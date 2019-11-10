package com.frank.sga.Utilidades;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.frank.sga.ui.login.LoginActivity;

public class MemoriaLocal {

    public static  Context CONTEXTOLOGIN = null;
    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
}
