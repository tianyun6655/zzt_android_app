package com.example.tianyunchen.zaozaotuandroid.untils;

import android.content.Context;

/**
 * Created by tianyunchen on 3/3/17.
 */

public class UserSharePreference extends SharePreferencesUtils {
    private final String USER_TOKEN ="user_token";
    private final String IS_FIRST ="is_first";
    private final static  String FILE_NAME="user_info";
    private static  UserSharePreference userSharePreference;

    private UserSharePreference(Context context, String FILE_NAME) {
        super(context, FILE_NAME);
    }

    public synchronized static UserSharePreference getInstance(Context context) {
        if (null == userSharePreference) {
            userSharePreference = new UserSharePreference(context,FILE_NAME);
        }
        return userSharePreference;
    }

    public UserSharePreference setFirstLaunch(boolean isFirst) {
        setBoolean(IS_FIRST,isFirst);
        return  userSharePreference;

    }
    public boolean getFirstlaunch() {
        return getBoolean(IS_FIRST);
    }

    public UserSharePreference setToken(String value) {
        setString(USER_TOKEN, value);
        return  userSharePreference;
    }

    public String getUSER_NAME_KEY() {
        return getString(USER_TOKEN);
    }



}
