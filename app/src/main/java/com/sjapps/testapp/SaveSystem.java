package com.sjapps.testapp;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveSystem {

    final static String SettingsFile = "SettingsConfig.json";

    static void SaveData(Context context, String FileName, String data){
        File file = new File(context.getFilesDir(), FileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(data);
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    static String LoadData(Context context,String FileName){
        File file = new File(context.getFilesDir(), FileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileReader fileReader = new FileReader(file.getAbsolutePath());


            StringBuilder builder = new StringBuilder();
            String jsonString = null;
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }
            bufferedReader.close();
            if (builder.toString().equals("") || builder.toString().equals("{}"))
                return null;

            return new String(builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static SettingsData loadSettings(Context context ) {
        String data = LoadData(context,SettingsFile);
        if (data == null)
            return new SettingsData();
        return new Gson().fromJson(data,SettingsData.class);
    }
    public static void saveSettings(Context context,SettingsData data){
        SaveData(context,SettingsFile,new Gson().toJson(data));
    };

}
