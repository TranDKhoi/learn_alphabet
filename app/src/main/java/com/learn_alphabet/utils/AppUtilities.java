package com.learn_alphabet.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.widget.Toast;

import com.learn_alphabet.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class AppUtilities {

    private static long backPressed = 0;

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


    public static void downloadFile(Activity mActivity, Bitmap bitmap) {
        if (PermissionUtilities.isPermissionGranted()) {

            Random rand = new Random();
            int n = 10000;
            n = rand.nextInt(n);
            String fileName = "Wallpaper-" + n;

            FileOutputStream out;
            String filePath = getFilename(fileName);
            try {
                out = new FileOutputStream(filePath);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
                Toast.makeText(mActivity, mActivity.getString(R.string.saved_to_gallery), Toast.LENGTH_SHORT).show();
                MediaScannerConnection.scanFile(mActivity,
                        new String[]{filePath}, null,
                        (path, uri) -> {
                        });

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(mActivity, mActivity.getString(R.string.ops_image), Toast.LENGTH_SHORT).show();
            }
        } else {
            PermissionUtilities.requestMediaPermission(mActivity);
        }
    }

    private static String getFilename(String fileName) {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/Download/", "DRAWING BOAR");
        if (!file.exists()) {
            file.mkdirs();
        }
        if (fileName.contains("/")) {
            fileName = fileName.replace("/", "\\");
        }
        return (file.getAbsolutePath() + "/" + fileName + ".png");
    }


}
