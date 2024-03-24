package com.learn_alphabet.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreference {
    public static final String PREFS_KEY_ADFREE = "pref_key_adfree";
    public static final String PREFS_KEY_AL = "pref_key";
    public static final String PREFS_KEY_IMAGE = "pref_key_image";
    public static final String PREFS_KEY_IMAGE_LINK = "pref_key_image_link";
    public static final String PREFS_KEY_IMAGE_NAME = "pref_key_image_name";
    public static final String PREFS_KEY_ISSHOWNEWAPP = "pref_key_isshownewapp";
    public static final String PREFS_KEY_LANG = "pref_key_lang";
    public static final String PREFS_KEY_NS = "pref_key_nevershow";
    public static final String PREFS_KEY_SOUND = "pref_key_sound";
    public static final String PREFS_NAME_ADFREE = "pref_name_adfree";
    public static final String PREFS_NAME_AL = "pref_name";
    public static final String PREFS_NAME_IMAGE = "pref_name_image";
    public static final String PREFS_NAME_IMAGE_LINK = "pref_name_image_link";
    public static final String PREFS_NAME_IMAGE_NAME = "pref_name_image_name";
    public static final String PREFS_NAME_ISSHOWNEWAPP = "pref_name_isshownewapp";
    public static final String PREFS_NAME_LANG = "pref_name_lang";
    public static final String PREFS_NAME_NS = "pref_name_nevershow";
    public static final String PREFS_NAME_SOUND = "pref_name_sound";
    public static final String PREF_KEY_FIRST_TIME = "first_time_key";
    public static final String PREF_KEY_LANG = "pref_key_lang";
    public static final String PREF_KEY_MUSIC = "key_music";
    public static final String PREF_KEY_PURCHASE = "BUY";
    public static final String PREF_KEY_SOUND = "key_sound";
    public static final String PREF_KEY_STORAGE_PERMISSION_NEVER = "key_storage_permission_never";
    public static final String PREF_NAME_FIRST_TIME = "first_time_name";
    public static final String PREF_NAME_LANG = "pref_name_lang";
    public static final String PREF_NAME_MUSIC = "name_music";
    public static final String PREF_NAME_PURCHASE = "SCORE";
    public static final String PREF_NAME_SOUND = "name_sound";
    public static final String PREF_NAME_STORAGE_PERMISSION_NEVER = "name_storage_permission_never";
    public String PREFS_KEY = "";
    public String PREFS_NAME = "";
    private SharedPreferences sharedPrefisShow;

    public SharedPreference(String str, String str2) {
        this.PREFS_NAME = str;
        this.PREFS_KEY = str2;
    }





    public boolean getSettingMusic(Context context) {
        return context.getSharedPreferences(PREF_NAME_MUSIC, 0).getBoolean(PREF_KEY_MUSIC, true);
    }

    public boolean getSettingSound(Context context) {
        return context.getSharedPreferences(PREF_NAME_SOUND, 0).getBoolean(PREF_KEY_SOUND, true);
    }

    public boolean getStoragePermissionNever(Context context) {
        return context.getSharedPreferences(PREF_NAME_STORAGE_PERMISSION_NEVER, 0).getBoolean(PREF_KEY_STORAGE_PERMISSION_NEVER, false);
    }

    public int getValue(Context context) {
        return context.getSharedPreferences(this.PREFS_NAME, 0).getInt(this.PREFS_KEY, 0);
    }

    public boolean getValueBool(Context context) {
        return context.getSharedPreferences(PREFS_NAME_ADFREE, 0).getBoolean(PREFS_NAME_ADFREE, false);
    }

    public void removeValue(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(this.PREFS_NAME, 0).edit();
        edit.remove(this.PREFS_KEY);
        edit.apply();
    }

    public void save(Context context, int i) {
        SharedPreferences.Editor edit = context.getSharedPreferences(this.PREFS_NAME, 0).edit();
        edit.putInt(this.PREFS_KEY, i);
        edit.apply();
    }

    public void saveBoolean(Context context, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREFS_NAME_ADFREE, 0).edit();
        edit.putBoolean(PREFS_NAME_ADFREE, z);
        edit.apply();
    }

    public void saveImage(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREFS_NAME_IMAGE, 0).edit();
        edit.putString(PREFS_KEY_IMAGE, str);
        edit.apply();
    }

    public void saveImageLink(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREFS_NAME_IMAGE_LINK, 0).edit();
        edit.putString(PREFS_KEY_IMAGE_LINK, str);
        edit.apply();
    }

    public void saveImageName(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREFS_NAME_IMAGE_NAME, 0).edit();
        edit.putString(PREFS_KEY_IMAGE_NAME, str);
        edit.apply();
    }

    public void saveLocale(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences("CommonPrefs", 0).edit();
        edit.putString("Language", str);
        edit.apply();
    }

    public void saveSettingFirstTime(Context context, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREF_NAME_FIRST_TIME, 0).edit();
        edit.putBoolean(PREF_KEY_FIRST_TIME, z);
        edit.apply();
    }

    public void saveSettingMusic(Context context, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREF_NAME_MUSIC, 0).edit();
        edit.putBoolean(PREF_KEY_MUSIC, z);
        edit.apply();
    }

    public void saveSettingSound(Context context, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREF_NAME_SOUND, 0).edit();
        edit.putBoolean(PREF_KEY_SOUND, z);
        edit.apply();
    }

    public void saveStoragePermissionNever(Context context, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREF_NAME_STORAGE_PERMISSION_NEVER, 0).edit();
        edit.putBoolean(PREF_KEY_STORAGE_PERMISSION_NEVER, z);
        edit.apply();
    }


}
