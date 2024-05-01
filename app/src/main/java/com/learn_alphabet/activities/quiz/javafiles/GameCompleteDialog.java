package com.learn_alphabet.activities.quiz.javafiles;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.learn_alphabet.R;
import com.learn_alphabet.activities.QuizActivity;
import com.learn_alphabet.activities.quiz.utils.QuizQuestionHandler;

public class GameCompleteDialog  implements OnClickListener {

    private final Activity act;
    private final int time;
    private final LayoutInflater inflater;

    private final Dialog dialog;
    private TextView txt_time, scoreTitle;
    private Button btn_cancel, btn_retry;
    private ImageView winningIcon, learnMore;
    public static int Score;

    private static final String ERROR= "Can't Left Blank";

    public GameCompleteDialog(Activity a, int time) {
        this.act = a;
        this.time = time;
        inflater = LayoutInflater.from(act);
        dialog = new Dialog(a);
    }

    public void buildDialog(){
        View v = inflater.inflate(R.layout.score_save_dialog, null, false);
        dialog.setContentView(v);
        dialog.setCancelable(false);
        this.findDialogViews(v);
    }

    public void showDialog(){
        this.buildDialog();
        this.dialog.show();
    }

    private  void findDialogViews(View view){
        txt_time = (TextView)view.findViewById(R.id.txt_score_time_value);
        scoreTitle = (TextView)view.findViewById(R.id.score_title);
        winningIcon = (ImageView) view.findViewById(R.id.winningicon);
        btn_cancel = (Button)view.findViewById(R.id.btn_score_cancel);
        btn_retry = (Button)view.findViewById(R.id.btn_score_retry);
        learnMore = (ImageView)view.findViewById(R.id.btn_learn_more);

        btn_cancel.setOnClickListener(this);
        btn_retry.setOnClickListener(this);

        this.renderViews();
    }

    @SuppressLint("SetTextI18n")
    private void renderViews() {
        txt_time.setText(time+" Points");
        if (time < Score){
            scoreTitle.setText("You Need More Practice");
            learnMore.setVisibility(View.VISIBLE);
            winningIcon.setVisibility(View.GONE);
        } else {
            scoreTitle.setText("Congrats !! you are Smart");
            learnMore.setVisibility(View.GONE);
            winningIcon.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_score_cancel) {
            this.dialog.dismiss();
            act.finish();
        } else if (v.getId() == R.id.btn_score_retry) {
            act.startActivity(new Intent(act, QuizActivity.class));
            Intent i = new Intent(act,QuizActivity.class);
            QuizQuestionHandler.populateList();
            QuizActivity.timer = 200;
            QuizActivity.QUESTION_LIMIT = 45;
            GameCompleteDialog.Score = 30;
            act.startActivity(i);
            this.dialog.dismiss();
            act.finish();
        }
    }

}
