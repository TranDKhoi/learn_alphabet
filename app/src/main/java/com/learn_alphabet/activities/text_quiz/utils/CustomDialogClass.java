package com.learn_alphabet.activities.text_quiz.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.cardview.widget.CardView;

import com.learn_alphabet.R;
import com.learn_alphabet.activities.text_quiz.ResultActivity;
import com.learn_alphabet.activities.text_quiz.TextQuizActivity;

public class CustomDialogClass extends Dialog implements View.OnClickListener {
    public Activity c;
    public Dialog d;

    public CardView no;
    public CardView yes;

    public CustomDialogClass(Activity activity) {
        super(activity);
        this.c = activity;
    }

    @Override 
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.dialog_custom);
        CardView cardView = (CardView) findViewById(R.id.btn_no);
        this.no = cardView;
        cardView.setOnClickListener(this);
    }

    @Override 
    public void onClick(View view) {
        if (view.getId() != R.id.btn_no) {
            return;
        }
        dismiss();
        Intent intent = new Intent(this.c, ResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("score", TextQuizActivity.getMyValue());
        intent.putExtras(bundle);
        this.c.startActivity(intent);
        this.c.finish();
    }
}
