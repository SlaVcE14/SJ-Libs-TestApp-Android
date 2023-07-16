package com.sjapps.testapp.sjdialog;

import android.view.View;

public class ListItem {
    String title;
    View.OnClickListener clickListener;

    public ListItem(String title, View.OnClickListener clickListener) {
        this.title = title;
        this.clickListener = clickListener;
    }
}
