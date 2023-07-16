package com.sjapps.testapp.sjdialog;

public class TestObj {

    String val1;
    String val2;

    public TestObj(String val1, String val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    @Override
    public String toString() {
        return "TestObj{" +
                "val1='" + val1 + '\'' +
                ", val2='" + val2 + '\'' +
                '}';
    }
}
