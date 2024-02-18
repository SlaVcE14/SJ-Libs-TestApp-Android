package com.sjapps.testapp.firework;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sjapps.library.firework.BuildConfig;
import com.sjapps.testapp.R;

public class FireworkActivity extends AppCompatActivity {


    public LinearLayout rootView;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firework);
        rootView = findViewById(R.id.root);

        TextView versionTxt = new TextView(this);
        versionTxt.setText("VersionName : " + BuildConfig.VERSION_NAME + " | VersionCode : " + BuildConfig.VERSION_CODE);
        versionTxt.setGravity(Gravity.CENTER);
        rootView.addView(versionTxt);

        Renderer renderer = new Renderer(this);
        rootView.addView(renderer);

    }
}