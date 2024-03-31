package com.learn_alphabet.activities.learn_speak;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.learn_alphabet.R;
import com.learn_alphabet.databinding.ActivityLearnBinding;

public class LearnActivity extends AppCompatActivity implements OnClickListener {
    private MediaPlayer mediaPlayer = null;
    LearnResourceSet resourceSet = new LearnResourceSet();
    private int totalItem = 0;
    private int currentPosition = 0;
    ActivityLearnBinding root;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        root = ActivityLearnBinding.inflate(getLayoutInflater());
        setContentView(root.getRoot());

        type = getIntent().getStringExtra("type");

        root.nextBtn.setOnClickListener(this);
        root.prevBtn.setOnClickListener(this);
        root.itemImg.setOnClickListener(this);

        totalItem = resourceSet.icon1Images.length;
        root.itemImg.setImageResource(resourceSet.icon1Images[currentPosition]);
        root.bgLayout.setBackgroundResource(R.drawable.abc_bg);
        root.bgcolorLayout.setBackgroundColor(Color.parseColor("#30054e"));

        mediaPlayer = MediaPlayer.create(this, resourceSet.icon1Sound[currentPosition]);
        mediaPlayer.start();

        updatePreviousButton();
    }

    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.itemImg) {
            if (currentPosition == totalItem - 1) {
                currentPosition = -1;
                gotoNext();
            }

            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            mediaPlayer = MediaPlayer.create(this, resourceSet.icon1Sound[currentPosition]);
            mediaPlayer.start();
            return;
        }
        if (id == R.id.nextBtn) {
            mediaPlayer.stop();
            gotoNext();
            return;
        }
        if (id == R.id.prevBtn) {
            mediaPlayer.stop();
            gotoPrevious();
            return;
        }

        if (id == R.id.backBtn) {
            LearnActivity.this.mediaPlayer.stop();
            exitByBackKey();
        }
    }

    private void gotoNext() {
        currentPosition++;
        updateNextButton();
        updatePreviousButton();
        if (currentPosition >= 0 && currentPosition < totalItem) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            root.itemImg.setImageResource(resourceSet.icon1Images[currentPosition]);
            mediaPlayer = MediaPlayer.create(this, resourceSet.icon1Sound[currentPosition]);
            mediaPlayer.start();
        }

    }

    private void updateNextButton() {
        if (currentPosition == totalItem - 1) {
            root.nextBtn.setAlpha(0.5f);
            root.nextBtn.setClickable(false);
            return;
        }
        root.nextBtn.setAlpha(1.0f);
        root.nextBtn.setClickable(true);
    }

    private void updatePreviousButton() {
        if (currentPosition == 0) {
            root.prevBtn.setAlpha(0.5f);
            root.prevBtn.setClickable(false);

            return;
        }
        root.prevBtn.setAlpha(1.0f);
        root.prevBtn.setClickable(true);
    }

    private void gotoPrevious() {
        currentPosition--;
        updateNextButton();
        updatePreviousButton();
        if (currentPosition >= 0 && currentPosition < totalItem) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            root.itemImg.setImageResource(resourceSet.icon1Images[currentPosition]);
            mediaPlayer = MediaPlayer.create(LearnActivity.this, resourceSet.icon1Sound[currentPosition]);
            mediaPlayer.start();
        }

    }

    public void onBackPressed() {
        super.onBackPressed();
        exitByBackKey();
    }

    protected void onUserLeaveHint() {
        mediaPlayer.stop();
        super.onUserLeaveHint();
    }

    protected void onPause() {
        super.onPause();
        if (!((PowerManager) getSystemService(POWER_SERVICE)).isScreenOn()) {
            mediaPlayer.stop();
        }
    }

    public void exitByBackKey() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
        finish();
    }
}
