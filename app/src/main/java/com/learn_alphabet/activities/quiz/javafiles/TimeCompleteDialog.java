package com.learn_alphabet.activities.quiz.javafiles;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.learn_alphabet.R;
import com.learn_alphabet.activities.QuizActivity;
import com.learn_alphabet.activities.quiz.utils.QuizQuestionHandler;

public class TimeCompleteDialog implements View.OnClickListener {

    private Activity act;
    private LayoutInflater inflater;

    /**UI Components*/

    private Dialog dialog;
    private TextView txt_msg;

    private Button btn_retry,btn_cancal;

    public TimeCompleteDialog(Activity a) {
        this.act = a;

        inflater = LayoutInflater.from(act);
        dialog = new Dialog(a);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private  void buildDialog(){
        View v=inflater.inflate(R.layout.time_complete_dialog,null,false);
        dialog.setContentView(v);
        dialog.setCancelable(false);
        this.findDialogViews(v);
    }

    public void showDialog(){
        this.buildDialog();
        this.dialog.show();
    }

    private  void findDialogViews(View view){
        txt_msg=(TextView)view.findViewById(R.id.txt_time_up);
        btn_retry=(Button)view.findViewById(R.id.btn_time_retry);
        btn_cancal=(Button)view.findViewById(R.id.btn_time_cancel);

        btn_retry.setOnClickListener(this);
        btn_cancal.setOnClickListener(this);

        /**Changing the state of the views*/
        this.renderViews();
    }

    private void renderViews() {
        txt_msg.setText("Sorry Time is up.\nTry Harder Next Time");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_time_retry) {
            Intent i = new Intent(act, QuizActivity.class);
            QuizQuestionHandler.populateList();
            QuizActivity.timer = 200;
            QuizActivity.QUESTION_LIMIT = 45;
            GameCompleteDialog.Score = 30;
            act.startActivity(i);
            this.dialog.dismiss();
            act.finish();
        } else if (v.getId() == R.id.btn_time_cancel) {
            this.dialog.dismiss();
            act.finish();
        }
    }

}