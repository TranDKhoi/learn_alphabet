package com.learn_alphabet.activities.text_quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.learn_alphabet.R;

public class ResultActivity extends Activity {
    Button btnHome;
    Button btnPlayAgain;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_result);

        int i = getIntent().getExtras().getInt("score");
        ((TextView) findViewById(R.id.textResult)).setText("Score " + i);
        Button button = (Button) findViewById(R.id.btnHome);
        this.btnHome = button;
        button.setOnClickListener(view -> {
            finish();
        });
        Button button2 = (Button) findViewById(R.id.btnPlayAgain);
        this.btnPlayAgain = button2;
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResultActivity.this.startActivity(new Intent(ResultActivity.this, TextQuizActivity.class));
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
