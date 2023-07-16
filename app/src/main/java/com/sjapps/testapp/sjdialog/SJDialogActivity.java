package com.sjapps.testapp.sjdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sjapps.library.BuildConfig;
import com.sjapps.testapp.R;
import com.sjapps.testapp.SettingsData;

import java.util.ArrayList;

public class SJDialogActivity extends AppCompatActivity {

    LinearLayout rootView;

    ArrayList<ListItem> items = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsData.applyTheme(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sjdialog);
        rootView = findViewById(R.id.root);
        SettingsData.applyWindowInsets(rootView);

        createList();

        addViews();

        TextView versionTxt = new TextView(this);
        versionTxt.setText("VersionName : " + BuildConfig.VERSION_NAME + " | VersionCode : " + BuildConfig.VERSION_CODE);
        versionTxt.setGravity(Gravity.CENTER);
        rootView.addView(versionTxt);

    }

    private void addViews() {

        for (ListItem sjDialog : items){

            Button button = new Button(new ContextThemeWrapper(this,getTheme()));
            button.setText(sjDialog.title);
            button.setOnClickListener(sjDialog.clickListener);
            rootView.addView(button);
        }
    }

    void createList(){

        items.add(new ListItem("basic dialog", view -> openActivity(SJDialog_BasicDialog.class)));
        items.add(new ListItem("message dialog", view -> openActivity(SJDialog_MessageDialog.class)));
        items.add(new ListItem("list dialog", view -> openActivity(SJDialog_ListDialog.class)));
        items.add(new ListItem("custom view dialog", view -> openActivity(SJDialog_CustomViewDialog.class)));

    }

    private void openActivity(Class<?> aClass){

        startActivity(new Intent(SJDialogActivity.this, aClass));

    }
//
//    private void createCustomViewDialogsList() {
//        startActivity(new Intent(SJDialogActivity.this, SJDialog_CustomViewDialog.class));
//    }
//
//    private void createListDialogsList() {
//        startActivity(new Intent(SJDialogActivity.this, SJDialog_ListDialog.class));
//    }
//
//    private void createMessageDialogsList() {
//        startActivity(new Intent(SJDialogActivity.this,SJDialog_MessageDialog.class));
//    }
//
//    private void createDialogsList() {
//       startActivity(new Intent(SJDialogActivity.this,SJDialog_SetupDialog.class));
//    }
}