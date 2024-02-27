package com.sjapps.testapp.sjdialog;

import android.graphics.Color;

import com.sjapps.library.customdialog.BasicDialog;
import com.sjapps.library.customdialog.CustomViewDialog;
import com.sjapps.library.customdialog.DialogPreset;
import com.sjapps.library.customdialog.ListDialog;
import com.sjapps.library.customdialog.MessageDialog;
import com.sjapps.testapp.R;

public class Presets {

    public static DialogPreset<BasicDialog> BasicDialogPreset1 = dialog -> {
        dialog.setOldTheme()
                .setTitle("Preset 1")
                .setMessage("Gray buttons and text color")
                .setButtonsColor(Color.GRAY)
                .setButtonsTextColor(Color.WHITE)
                .setTextColor(Color.GRAY);
    };
    public static DialogPreset<BasicDialog> BasicDialogPreset2 = dialog -> {
        dialog.setTitle("Preset 2")
                .setMessage("Change background, buttons and text color")
                .setDialogBackgroundColor(Color.BLUE)
                .setTextColor(Color.WHITE)
                .setButtonsColor(Color.WHITE)
                .setButtonsTextColor(Color.BLUE);
    };
    public static DialogPreset<BasicDialog> BasicDialogPreset3 = dialog -> {
        dialog.setTitle("Preset 3")
                .setMessage("change background and buttons resources")
                .setDialogBackgroundResource(R.drawable.test1234)
                .setButtonsBackgroundResource(R.drawable.test123);
    };

    public static DialogPreset<MessageDialog> MessageDialogPreset1 = dialog -> {
        dialog.setOldTheme()
                .setTitle("Preset 1")
                .setMessage("Gray button and text color")
                .setButtonColor(Color.GRAY)
                .setButtonTextColor(Color.WHITE)
                .setTextColor(Color.GRAY);
    };
    public static DialogPreset<MessageDialog> MessageDialogPreset2 = dialog -> {
        dialog.setTitle("Preset 2")
                .setMessage("Change background, button and text color")
                .setDialogBackgroundColor(Color.BLUE)
                .setTextColor(Color.WHITE)
                .setButtonColor(Color.WHITE)
                .setButtonTextColor(Color.BLUE);
    };
    public static DialogPreset<MessageDialog> MessageDialogPreset3 = dialog -> {
        dialog.setTitle("Preset 3")
                .setMessage("change background and button resources")
                .setDialogBackgroundResource(R.drawable.test1234)
                .setButtonBackgroundResource(R.drawable.test123);
    };

    public static DialogPreset<ListDialog> ListDialogPreset1 = dialog -> {
        dialog.setOldTheme()
                .setTitle("Preset 1")
                .setMessage("Gray buttons and text color")
                .setButtonsColor(Color.GRAY)
                .setButtonsTextColor(Color.WHITE)
                .setTextColor(Color.GRAY);
    };
    public static DialogPreset<ListDialog> ListDialogPreset2 = dialog -> {
        dialog.setTitle("Preset 2")
                .setMessage("Change background, buttons and text color")
                .setDialogBackgroundColor(Color.BLUE)
                .setTextColor(Color.WHITE)
                .setButtonsColor(Color.WHITE)
                .setButtonsTextColor(Color.BLUE)
                .setListItemBackgroundColor(Color.BLUE)
                .setListItemSelectedBackgroundColor(Color.CYAN)
                .setListItemTextColor(Color.WHITE);
    };
    public static DialogPreset<ListDialog> ListDialogPreset3 = dialog -> {
        dialog.setTitle("Preset 3")
                .setMessage("change background and buttons resources")
                .setDialogBackgroundResource(R.drawable.test1234)
                .setButtonsBackgroundResource(R.drawable.test123)
                .setListItemBackgroundResource(R.drawable.test1234)
                .setListItemSelectedBackgroundResource(R.drawable.test123);
    };

    public static DialogPreset<CustomViewDialog> CustomViewDialogPreset1 = dialog -> {
        dialog.setOldTheme()
                .setTitle("Preset 1")
                .setMessage("Gray buttons and text color")
                .setButtonsColor(Color.GRAY)
                .setButtonsTextColor(Color.WHITE)
                .setTextColor(Color.GRAY);
    };
    public static DialogPreset<CustomViewDialog> CustomViewDialogPreset2 = dialog -> {
        dialog.setTitle("Preset 2")
                .setMessage("Change background, buttons and text color")
                .setDialogBackgroundColor(Color.BLUE)
                .setTextColor(Color.WHITE)
                .setButtonsColor(Color.WHITE)
                .setButtonsTextColor(Color.BLUE);
    };
    public static DialogPreset<CustomViewDialog> CustomViewDialogPreset3 = dialog -> {
        dialog.setTitle("Preset 3")
                .setMessage("change background and buttons resources")
                .setDialogBackgroundResource(R.drawable.test1234)
                .setButtonsBackgroundResource(R.drawable.test123);
    };

}
