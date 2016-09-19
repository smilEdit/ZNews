package com.zzz.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @创建者 zlf
 * @创建时间 2016/9/1 14:20
 */
public class ZSp {

    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("oschina", Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    public static String getString(Context context,String key,String defValue) {
        SharedPreferences sp = context.getSharedPreferences("oschina", Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void saveString(Context context,String key,String value) {
        SharedPreferences sp = context.getSharedPreferences("oschina", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    public static int getInt(Context context,String key) {
        SharedPreferences sp = context.getSharedPreferences("oschina", Context.MODE_PRIVATE);
        return sp.getInt(key, 0);
    }

    public static int getInt(Context context,String key,int defValue) {
        SharedPreferences sp = context.getSharedPreferences("oschina", Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static void saveInt(Context context,String key,int value) {
        SharedPreferences sp = context.getSharedPreferences("oschina", Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }

    public static boolean getBoolean(Context context,String key) {
        SharedPreferences sp = context.getSharedPreferences("oschina", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    public static boolean getBoolean(Context context,String key,boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences("oschina", Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void saveBoolean(Context context,String key,int value) {
        SharedPreferences sp = context.getSharedPreferences("oschina", Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }


}
