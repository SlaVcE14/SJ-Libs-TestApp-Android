package com.sjapps.testapp.sjdialog;

import android.widget.CompoundButton;

public class CBData {
    public String text;
    public CompoundButton.OnCheckedChangeListener onCheckedChangeListener;


    public CBData(String text){
        this.text = text;
    }

    public CBData(String text, CompoundButton.OnCheckedChangeListener onCheckedChangeListener){
        this.text = text;
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

}
