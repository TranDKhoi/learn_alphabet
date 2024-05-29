package com.learn_alphabet.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;

public class PermissionUtilities {


    public static boolean isPermissionGranted() {
        return Environment.isExternalStorageManager();
    }

    public static void requestMediaPermission(Activity a) {
        if (!Environment.isExternalStorageManager()) {
            //request for the permission
            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            Uri uri = Uri.fromParts("package", a.getPackageName(), null);
            intent.setData(uri);
            a.startActivity(intent);
        }
    }
}
