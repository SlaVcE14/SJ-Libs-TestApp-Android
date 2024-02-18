package com.sjapps.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.color.DynamicColors;
import com.sjapps.library.customdialog.MessageDialog;
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
//        MessageDialog dialog = new MessageDialog();
//        dialog.Builder(this,true)
//                .setTitle("testtt")
//                .setMessage("Gdfg")
////                .setDialogBackgroundResource(com.sjapps.library.R.drawable.dialog_background)
////                .setButtonBackgroundResource(com.sjapps.library.R.drawable.ripple_button_old)
//                .setMaxDialogWidth(300)
//                .onButtonClick(dialog::dismiss)
//                .show();

//        View view = getLayoutInflater().inflate(R.layout.test,null);

//        CustomViewDialog customViewDialog = new CustomViewDialog();
//        customViewDialog.Builder(this)
//                .addCustomView(view)
//                .show();

        String[] str = {"test","test124","zzz"};
        Integer[] ints = {1,2,3};
        ArrayList<String> strings = new ArrayList<>();
        strings.add("aaa");
        strings.add("aaa2");
        strings.add("aaa3");
        strings.add("aaa5");

        ArrayList<testClass> arrayList = new ArrayList<>();
        arrayList.add(new testClass("test","test124"));
        arrayList.add(new testClass("test2","test12456"));
        arrayList.add(new testClass("test3","test124566789"));

        testClass[] testClasses = {new testClass("test1","val1"),new testClass("test2","val2"),new testClass("test3","val3"),new testClass("test4","val4")};

        CustomListAdapter customListAdapter = new CustomListAdapter(str);




        /*ListDialog dialog = new ListDialog();
        dialog.Builder(this,true)
                .setTitle("List dialog")
                .setSelectableList()
                .setMessage("this is a message")
                .setListBackgroundResource(R.drawable.testbtn)
                .setListItemBackgroundResource(R.drawable.test123)
                .setListItemSelectedBackgroundResource(R.drawable.test1234)

//                .setItems(str)
//                .setItems(str,((position, value) -> System.out.println(position + ", " + value)))
//                .setItems(ints, String::valueOf)
//                .setItems(testClasses, new ListItemValues<testClass>() {
//                    @Override
//                    public String getValue1(testClass obj) {
//                        return obj.val1;
//                    }
//
//                    @Override
//                    public String getValue2(testClass obj) {
//                        return obj.val2;
//                    }
//                })
//                .setItems(arrayList,obj -> obj.val1)
//                .setItems(arrayList, new ListItemValues<testClass>() {
//                    @Override
//                    public String getValue1(testClass obj) {
//                        return obj.val1;
//                    }
//
//                    @Override
//                    public String getValue2(testClass obj) {
//                        return obj.val2;
//                    }
//                })
//                .setItems(ints, String::valueOf,(position, obj) -> System.out.println(position + ", " + obj))
//                .setItems(testClasses,obj-> obj.val1,(position, obj) -> System.out.println(position + ", " + obj))
//                .setItems(arrayList,obj -> obj.val1, (position, obj) -> System.out.println(position + ", " + obj))
                .setItems(arrayList, new ListItemValues<testClass>() {
                    @Override
                    public String getValue1(testClass obj) {
                        return obj.val1;
                    }

                    @Override
                    public String getValue2(testClass obj) {
                        return obj.val2;
                    }
                }, (position, obj) -> System.out.println(position + ", " + obj))

//                .setAdapter(customListAdapter)

                .onButtonClick(() -> System.out.println(dialog.getSelectedItems()))

                .show();*/

//        SetupDialog dialog = new SetupDialog();
//        dialog.DialogBuilder(this,R.style.Theme_TestApp)
//                .setTitle("aaa")
////                .setRightButtonColor(SJDialog.RED_BUTTON)
////                .setOldTheme()
//                .show();
//
//        SetupDialog dialog2 = new SetupDialog();
//        dialog2.DialogBuilder(this)
//                .setTitle("aaa")
////                .setRightButtonColor(SJDialog.RED_BUTTON)
////                .setOldTheme()
//                .show();


    }


    public void openSJDialog(View view) {
        startActivity(new Intent(MainActivity.this, SJDialogActivity.class));
    }

    public void openFirework(View view) {
        startActivity(new Intent(MainActivity.this, FireworkActivity.class));
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