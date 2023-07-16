package com.sjapps.testapp;

import android.content.Context;
import android.view.View;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingsData {
    int theme = 0;

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public static int applyTheme(Context context){
        SettingsData data = SaveSystem.loadSettings(context);

        switch (data.getTheme()){
            case 0:
                return R.style.Theme_TestApp;

            case 1:
                return R.style.Theme_TestApp_insets;

        }
        return R.style.Theme_TestApp;
    }


    public static void applyWindowInsets(View view) {

        ViewCompat.setOnApplyWindowInsetsListener(view,(v, insets) -> {
            Insets insetsNB = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets insetsDC = insets.getInsets(WindowInsetsCompat.Type.displayCutout());

            Insets insetsFinal = Insets.of(
                    insetsNB.left + insetsDC.left,
                    insetsNB.top != 0? insetsNB.top : insetsDC.top,
                    insetsNB.right + insetsDC.right,
                    insetsNB.bottom + insetsDC.bottom
            );

            v.setPadding(insetsFinal.left, insetsFinal.top, insetsFinal.right, insetsFinal.bottom);

            return WindowInsetsCompat.CONSUMED;
        });
    }

}
