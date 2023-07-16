package com.sjapps.testapp.sjdialog;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class ExampleOnTouchListener implements View.OnTouchListener {

    Context context;

    public ExampleOnTouchListener(Context context){
        this.context = context;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP){
            Toast.makeText(context, "Example onTouchListener", Toast.LENGTH_SHORT).show();
            System.out.println("aaaaaaaaaaaa");
        }

        return true;
    }
}
