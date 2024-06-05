package com.learn_alphabet.activities.text_quiz;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.learn_alphabet.R;

public class WonActivity extends Activity {
    Button btnHome;
    Button btnPlayAgain;
    TextView tv;

    @Override 
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_won);
        this.tv = (TextView) findViewById(R.id.congo);
        int i = getIntent().getExtras().getInt("score");
        TextView textView = this.tv;
        textView.setText("FINAL SCORE:" + i);
        MediaPlayer create = MediaPlayer.create(this, (int) R.raw.win);
        create.setVolume(100.0f, 100.0f);
        create.start();
        Button button = (Button) findViewById(R.id.btnHome);
        this.btnHome = button;
        button.setOnClickListener(view -> WonActivity.this.finish());
        Button button2 = (Button) findViewById(R.id.btnPlayAgain);
        this.btnPlayAgain = button2;
        button2.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                WonActivity.this.startActivity(new Intent(WonActivity.this, TextQuizActivity.class));
            }
        });
    }

    @Override 
    protected void onStart() {
        super.onStart();
    }

    @Override 
    protected void onResume() {
        super.onResume();
    }
}
