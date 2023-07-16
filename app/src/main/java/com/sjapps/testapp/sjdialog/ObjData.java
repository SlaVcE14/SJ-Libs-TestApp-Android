package com.sjapps.testapp.sjdialog;

public class ObjData {
    String name;
    Object value;

    public ObjData(String name, Object aBoolean) {
        this.name = name;
        this.value = aBoolean;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\":\"" + name + '\"' +
                ", \"value\":\"" + value +
                "\"}";
    }
}
