package com.sjapps.testapp.sjdialog;

import android.util.Log;

public class LogDialog {

    public static void txt(String name,String value){
        Log.w("CreateDialog",name + " : " + value);
    }
    public static void bool(String name,boolean value){
        if (value)
            Log.i("CreateDialog",value + " : " + name);
        else
            Log.e("CreateDialog",value + " : " + name);
    }
    public static void line(){
        Log.d("CreateDialog","---------------------------------");
    }
}
