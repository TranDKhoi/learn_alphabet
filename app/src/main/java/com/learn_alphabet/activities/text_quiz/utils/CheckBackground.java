package com.learn_alphabet.activities.text_quiz.utils;

import android.app.ActivityManager;
import android.content.Context;
import java.util.List;


public class CheckBackground {
    public static boolean appInBackground(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        return (runningTasks.isEmpty() || runningTasks.get(0).topActivity.getPackageName().equals(context.getPackageName())) ? false : true;
    }
}
