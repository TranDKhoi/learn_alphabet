package com.learn_alphabet.activities.quiz.javafiles;

import android.media.MediaPlayer;

public class MediaPlayerListener implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.release();

    }
    @Override
    public void onPrepared(MediaPlayer mp) {
        // TODO Auto-generated method stub
        mp.start();
    }

}
