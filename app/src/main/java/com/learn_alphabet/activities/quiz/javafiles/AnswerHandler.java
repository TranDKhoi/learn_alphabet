package com.learn_alphabet.activities.quiz.javafiles;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.widget.TextView;

import com.learn_alphabet.R;

public class AnswerHandler {

    public static void rightAnswerHandler(TextView tv, Activity act, int counter, int limit) {
        tv.setText(counter+"/"+limit);
    }

    public static void wrongAnswerPlayer(Activity act) {
        MediaPlayer player=MediaPlayer.create(act, R.raw.wrong);
        player.start();
        player.setOnCompletionListener(new MediaPlayerListener());
        vibrate(act);
    }
    public static void rightAnswerPlayer(Activity act) {
        MediaPlayer player = MediaPlayer.create(act, R.raw.right);
        player.start();
        player.setOnCompletionListener(new MediaPlayerListener());
    }

    public static void finalAnswerPlayer(Activity act) {
        MediaPlayer player = MediaPlayer.create(act, R.raw.applause);
        player.start();
        player.setOnCompletionListener(new MediaPlayerListener());
    }

    public static void vibrate(Activity act){
        Vibrator v = (Vibrator) act.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);
    }

}
