package com.sjapps.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity {

    ArrayAdapter<CharSequence> Themes;
    Spinner ThemeSpinner;
    SettingsData settingsData;
    int ThemeSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(SettingsData.applyTheme(this));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SettingsData.applyWindowInsets(findViewById(R.id.main));
        ThemeSpinner = findViewById(R.id.theme_spinner);
        Themes = ArrayAdapter.createFromResource(this, R.array.themes, android.R.layout.simple_spinner_item);
        Themes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ThemeSpinner.setAdapter(Themes);
        settingsData = SaveSystem.loadSettings(this);
        ThemeSelected = settingsData.getTheme();
        ThemeSpinner.setSelection(ThemeSelected);
        ThemeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (ThemeSelected == position)
                    return;
                ThemeSelected = position;
                settingsData.setTheme(position);
                SaveSystem.saveSettings(SettingsActivity.this, settingsData);
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                finishAffinity();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}