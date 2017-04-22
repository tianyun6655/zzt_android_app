package com.example.tianyunchen.zaozaotuandroid.untils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tianyunchen on 3/2/17.
 */

public class SharePreferencesUtils {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    protected SharePreferencesUtils(Context context,String FILE_NAME) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    protected void setString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    protected String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    protected void setBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    protected boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    protected void setInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    protected int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }
}
