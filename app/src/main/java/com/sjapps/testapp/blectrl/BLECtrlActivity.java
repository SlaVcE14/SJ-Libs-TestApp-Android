package com.sjapps.testapp.blectrl;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.sj14apps.library.blectrl.BluetoothController;
import com.sj14apps.library.blectrl.BluetoothStatus;
import com.sjapps.testapp.R;

import java.util.ArrayList;

public class BLECtrlActivity extends AppCompatActivity {

    private MaterialButton selectButton;
    private MaterialButton connectButton;
    private MaterialButton sendButton;

    private TextView deviceText;
    private TextView consoleText;

    private TextInputEditText commandInput;

    private BluetoothController bluetoothController;

    private ActivityResultLauncher<String[]> permissionLauncher;
    private ActivityResultLauncher<Intent> enableBtLauncher;

    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_blectrl);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets ime = insets.getInsets(WindowInsetsCompat.Type.ime());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom + ime.bottom);

            return insets;
        });


        selectButton = findViewById(R.id.selectButton);
        connectButton = findViewById(R.id.connectButton);
        sendButton = findViewById(R.id.sendButton);

        deviceText = findViewById(R.id.deviceText);
        consoleText = findViewById(R.id.consoleText);

        commandInput = findViewById(R.id.commandInput);


        bluetoothController = new BluetoothController(bleCallBack, permissionCallBack);

        setupPermissions();

        bluetoothController.setupBluetooth();


        selectButton.setOnClickListener(v -> {

            if (!hasRequiredPermissions()) {
                checkAndRequestPermissions();
                return;
            }

            bluetoothController.loadPairedDevices(() -> {
                showDeviceDialog();
            });


        });


        connectButton.setOnClickListener(v -> {

            if (bluetoothController.isConnected()) {
                bluetoothController.disconnect();
                return;
            }

            BluetoothDevice device = bluetoothController.getSelectedDevice();

            if (device == null) {
                appendConsole("> No device selected");
                return;
            }

            appendConsole("> Connecting to " + bluetoothController.getDeviceName(device));

            bluetoothController.connectDevice(this);
        });


        sendButton.setOnClickListener(v -> {

            String command = commandInput.getText().toString().trim();

            if (command.isEmpty()) {
                return;
            }

            appendConsole("$ " + command);

            bluetoothController.sendData(command + "\n");

            commandInput.setText("");
        });
    }


    private void scanDevices() {

        appendConsole("> Starting BLE scan...");

        bluetoothController.scanDevices(this);
    }


    private void showDeviceDialog() {

        ArrayList<BluetoothDevice> devices = bluetoothController.getDevices();

        if (devices.isEmpty()) {

            appendConsole("> No devices found");

            new AlertDialog.Builder(this)
                    .setTitle("BLE Devices")
                    .setMessage("No devices found.")
                    .setPositiveButton("OK", null)
                    .show();

            return;
        }


        String[] deviceNames = new String[devices.size()];

        for (int i = 0; i < devices.size(); i++) {

            BluetoothDevice device = devices.get(i);

            deviceNames[i] = bluetoothController.getDeviceName(device);
        }


        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }


        dialog = new AlertDialog.Builder(this)
                .setTitle("Select BLE Device")
                .setItems(deviceNames, (dialog, which) -> {

                    BluetoothDevice device = devices.get(which);

                    bluetoothController.selectDevice(device);

                    String name = bluetoothController.getDeviceName(device);

                    deviceText.setText(name + "\n" + device.getAddress());

                    appendConsole("> Selected: " + name);
                })
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Scan", (dialog, which) -> scanDevices()).show();
    }


    private void setupPermissions() {

        permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {

            boolean allGranted = true;

            for (Boolean granted : result.values()) {

                if (!granted) {
                    allGranted = false;
                    break;
                }
            }

            if (allGranted) {
                onPermissionsReady();
            } else {
                appendConsole("> Bluetooth & Location permissions are required");
            }
        });


        enableBtLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {

            if (bluetoothController.isBluetoothAvailable()) {

                onPermissionsReady();

            } else {

                appendConsole("> Bluetooth must be enabled");
            }
        });


        checkAndRequestPermissions();
    }


    private void checkAndRequestPermissions() {

        String[] permissions;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            permissions = new String[]{Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.ACCESS_FINE_LOCATION};

        } else {

            permissions = new String[]{Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.ACCESS_FINE_LOCATION};
        }


        boolean needPermission = false;

        for (String permission : permissions) {

            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {

                needPermission = true;
                break;
            }
        }


        if (needPermission) {

            permissionLauncher.launch(permissions);

        } else {

            onPermissionsReady();
        }
    }


    private void onPermissionsReady() {

        if (bluetoothController.getBluetoothAdapter() == null) {
            return;
        }


        if (!bluetoothController.getBluetoothAdapter().isEnabled()) {

            enableBtLauncher.launch(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE));

            return;
        }


        bluetoothController.registerDiscoveryReceiver(this);

        bluetoothController.loadPairedDevices(() -> {});
    }


    private boolean hasRequiredPermissions() {

        return hasBluetoothPermission() && hasScanPermission();
    }


    private boolean hasBluetoothPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            return ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED;
        }

        return ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED;
    }


    private boolean hasScanPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            return ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED;
        }

        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }


    private void appendConsole(String text) {

        consoleText.append(text + "\n");

        consoleText.post(() -> {

            if (consoleText.getParent() instanceof android.widget.ScrollView) {

                ((android.widget.ScrollView) consoleText.getParent()).fullScroll(android.widget.ScrollView.FOCUS_DOWN);
            }
        });
    }


    @Override
    protected void onDestroy() {

        if (bluetoothController != null) {

            bluetoothController.disconnect();

            bluetoothController.unregisterDiscoveryReceiver(this);
        }

        super.onDestroy();
    }


    private final BluetoothController.CallBack bleCallBack = new BluetoothController.CallBack() {

        @Override
        public void onConnect() {

            appendConsole("> Connected");
        }


        @Override
        public void onDisconnect() {

            appendConsole("> Disconnected");
        }


        @Override
        public void onDataReceived(String data) {

            appendConsole("< " + data);
        }


        @Override
        public void onStatusUpdate(BluetoothStatus status) {

            appendConsole("[BLE] " + status.status + ": " + status.message);


            switch (status.status) {

                case SCANNING:
                    selectButton.setText("Scanning...");
                    break;

                case SCAN_FINISHED:
                case FAILED_SCANNING:

                    selectButton.setText("Select");

                    showDeviceDialog();

                    break;

                case CONNECTING:
                    connectButton.setText("Connecting...");
                    break;

                case CONNECTED:
                    connectButton.setText("Disconnect");
                    break;

                case DISCONNECTED:
                    connectButton.setText("Connect");
                    break;
            }
        }
    };


    private final BluetoothController.CheckPermission permissionCallBack = new BluetoothController.CheckPermission() {

        @Override
        public boolean hasBluetoothPermission() {

            return BLECtrlActivity.this.hasBluetoothPermission();
        }


        @Override
        public boolean hasScanPermission() {

            return BLECtrlActivity.this.hasScanPermission();
        }
    };
}