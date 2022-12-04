package com.example.milkyway.api;

import android.content.Context;
import android.content.SharedPreferences;

public class Tokens {
    private static final String PREFS = "prefs";
    private static final String Access_Token = "Access_Token";
    private static final String Refresh_Token = "Refresh_Token";
    private Context mContext;
    private static SharedPreferences prefs;
    private static SharedPreferences.Editor prefsEditor;
    private static Tokens instance;

    public static synchronized Tokens init(Context context) {
        if(instance == null)
            instance = new Tokens(context);
        return instance;
    }

    private Tokens(Context context) {
        mContext = context;
        prefs = mContext.getSharedPreferences(PREFS,Context.MODE_PRIVATE);
        prefsEditor = prefs.edit();
    }

    public static void setAccessToken(String value) {
        prefsEditor.putString(Access_Token, value).commit();
    }

    public static String getAccessToken(String defValue) {
        return prefs.getString(Access_Token,defValue);
    }

    public static void setRefreshToken(String value) {
        prefsEditor.putString(Refresh_Token, value).commit();
    }

    public static String getRefreshToken(String defValue) {
        return prefs.getString(Refresh_Token,defValue);
    }

    public static void clearToken() {
        prefsEditor.clear().apply();
    }
}