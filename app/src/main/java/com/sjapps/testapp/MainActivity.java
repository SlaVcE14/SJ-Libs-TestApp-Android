package com.sjapps.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.color.DynamicColors;
import com.sjapps.library.customdialog.MessageDialog;
import com.sjapps.testapp.blectrl.BLECtrlActivity;
import com.sjapps.testapp.firework.FireworkActivity;
import com.sjapps.testapp.sjdialog.CustomListAdapter;
import com.sjapps.testapp.sjdialog.SJDialogActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsData.applyTheme(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SettingsData.applyWindowInsets(findViewById(R.id.main));
        Log.d("TAG", "onCreate123: " + DynamicColors.isDynamicColorAvailable());

    }


    public void openSJDialog(View view) {
        startActivity(new Intent(MainActivity.this, SJDialogActivity.class));
    }

    public void openFirework(View view) {
        startActivity(new Intent(MainActivity.this, FireworkActivity.class));
    }

    public void openBLECtrl(View view) {
        startActivity(new Intent(MainActivity.this, BLECtrlActivity.class));
    }

    public void openSettings(View view) {
        startActivity(new Intent(MainActivity.this,SettingsActivity.class));


    }
}

class testClass{

    String val1;
    String val2;

    public testClass(String val1, String val2){
        this.val1 = val1;
        this.val2 = val2;

    }

    @Override
    public String toString() {
        return "testClass{" +
                "val1='" + val1 + '\'' +
                ", val2='" + val2 + '\'' +
                '}';
    }
}