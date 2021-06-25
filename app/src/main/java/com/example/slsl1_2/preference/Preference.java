package com.example.slsl1_2.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class Preference {
    public static final String STORAGE_NAME = "Storage Name";
    private static SharedPreferences settings = null;
    private static SharedPreferences.Editor editor = null;
    private static Context context = null;

    public static void init1(Context cnt) {
        context = cnt;
    }

    private static void init() {
        settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
    }

    public static void addProperty(String name, String value) {
        if (settings == null) {
            init();
        }
        editor.putString(name, value);
        editor.apply();
    }

    public static String getProperty(String name) {
        if (settings == null) {
            init();
        }
        return settings.getString(name, null);
    }
}
