package com.learn_alphabet.activities;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.learn_alphabet.R;
import com.learn_alphabet.activities.quiz.BaseFragmentActivity;
import com.learn_alphabet.activities.quiz.interfaces.AnswerListener;
import com.learn_alphabet.activities.quiz.interfaces.OnTimeCompleteListener;
import com.learn_alphabet.activities.quiz.javafiles.AnswerFinder;
import com.learn_alphabet.activities.quiz.javafiles.AnswerHandler;
import com.learn_alphabet.activities.quiz.javafiles.CurrentQuestion;
import com.learn_alphabet.activities.quiz.javafiles.GameCompleteDialog;
import com.learn_alphabet.activities.quiz.javafiles.MediaPlayerListener;
import com.learn_alphabet.activities.quiz.javafiles.TimeCompleteDialog;
import com.learn_alphabet.activities.quiz.javafiles.UtilityFile;
import com.learn_alphabet.activities.quiz.utils.QuizQuestionHandler;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends BaseFragmentActivity implements View.OnClickListener, AnswerListener, OnTimeCompleteListener {

    private TextView questionTextView, timer_text, score_text;
    private ImageView option_1, option_2, option_3, option_4, ImageAudio;
    private LinearLayout layout;
    ProgressBar progressBar;
    ProgressBar progressBarScore;
    private Handler handler = new Handler();
    public static int timer;
    private OnTimeCompleteListener timeComplete = (OnTimeCompleteListener)this;
    private int QUESTION_COUNTER = 1;
    private int SCORE_COUNTER = 0;
    public static int QUESTION_LIMIT;
    String Data;
    private MediaPlayer player = null;
    private CurrentQuestion currentQuestion;
    ImageView backbtn;
    UtilityFile utility;

    private Runnable timerThread = new Runnable() {
        @Override
        public void run() {
            if(timer > 0) {
                timer_text.setText("" + timer );
                progressBar.setProgress(timer);
                timer--;
                handler.postDelayed(timerThread, 1000);
            } else {
                timeComplete.onTimeFinish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity_layout);
        player = new MediaPlayer();

        layout=(LinearLayout)this.findViewById(R.id.subject_animate1);
        score_text=(TextView)this.findViewById(R.id.score);
        timer_text=(TextView)this.findViewById(R.id.time);
        //timer_text.setText("Time : " + timer);
        score_text.setText("0/" + QUESTION_LIMIT);


        ImageAudio = (ImageView) this.findViewById(R.id.ImageAudio);
        questionTextView = (TextView) this.findViewById(R.id.quiz_question);
        option_1 = (ImageView) this.findViewById(R.id.quiz_level_option_1);
        option_2 = (ImageView) this.findViewById(R.id.quiz_level_option_2);
        option_3 = (ImageView) this.findViewById(R.id.quiz_level_option_3);
        option_4 = (ImageView) this.findViewById(R.id.quiz_level_option_4);
        backbtn = (ImageView) this.findViewById(R.id.backbtn);


        option_1.setOnClickListener(this);
        option_2.setOnClickListener(this);
        option_3.setOnClickListener(this);
        option_4.setOnClickListener(this);
        backbtn.setOnClickListener(this);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        /**Utility for formating of the question*/
        utility=new UtilityFile();
        refreshQuestion();
        handler.postDelayed(timerThread, 10);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setMax(timer);
        progressBarScore = (ProgressBar) findViewById(R.id.progress_bar_score);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void refreshQuestion(){
        List<Integer> randomOptionOrdering=new ArrayList<Integer>();
        currentQuestion = utility.setCurrentQuestionSet(QuizQuestionHandler.questionSet);

        ImageView buttons[] = { option_1, option_2, option_3, option_4 };
        int answerIndex = utility.randInt(0, 3);

        buttons[answerIndex].setImageDrawable(getResources().getDrawable(currentQuestion.getCurrentSet().getAnsImage()));
        buttons[answerIndex].setTag((Object)currentQuestion.getCurrentSet().getId());
        questionTextView.setText("" + currentQuestion.getCurrentSet().getQuestion());
        ImageAudio.setImageResource(R.drawable.speaker);
        ImageAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartPlay();
            }
        });

        buttons[randomOrder(randomOptionOrdering, answerIndex)].setImageDrawable(getResources().getDrawable(currentQuestion.getOption_1().getAnsImage()));
        buttons[randomOrder(randomOptionOrdering, answerIndex)].setImageDrawable(getResources().getDrawable(currentQuestion.getOption_2().getAnsImage()));
        buttons[randomOrder(randomOptionOrdering, answerIndex)].setImageDrawable(getResources().getDrawable(currentQuestion.getOption_3().getAnsImage()));
    }

    @Override
    public void onClick(View v) {
        disableOptionButton();
        AnswerFinder finder = null;

        if (v.getId() == R.id.quiz_level_option_1) {
            finder=new AnswerFinder(option_1, currentQuestion , this);
            finder.getAnswer();
            return;
        }

        if (v.getId() == R.id.quiz_level_option_2) {
            finder = new AnswerFinder(option_2, currentQuestion , this);
            finder.getAnswer();
            return;
        }

        if (v.getId() == R.id.quiz_level_option_3) {
            finder = new AnswerFinder(option_3, currentQuestion , this);
            finder.getAnswer();
            return;
        }

        if (v.getId() == R.id.quiz_level_option_4) {
            finder = new AnswerFinder(option_4, currentQuestion , this);
            finder.getAnswer();
        }
    }

    public void animateNext(){
        Animation anim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.right_to_left);
        layout.startAnimation(anim);
        anim.setDuration(200);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                stopTimer();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                Animation anim=AnimationUtils.loadAnimation(getBaseContext(), R.anim.left_to_right);
                anim.setDuration(200);
                refreshQuestion();
                layout.startAnimation(anim);
                startTimer();
            }
        });
    }

    public void startTimer(){
        handler.postDelayed(timerThread, 100);
    }
    public void stopTimer(){
        handler.removeCallbacks(timerThread);
    }

    public int randomOrder(List<Integer> rand,int currentAnswerIndex){
        while(true){
            int index = new UtilityFile().randInt(0,3);
            if(index != currentAnswerIndex){
                if (!isInserted(rand, index)) {
                    rand.add(index);
                    return index;
                }
            }
        }
    }

    public  boolean isInserted(List<Integer> inserted,int currentIndex){
        for(Integer inte:inserted){
            if(inte == currentIndex){
                return true;
            }
        }
        return false;
    }

    private void StartPlay() {
        player = new MediaPlayer();
        Data = String.valueOf(currentQuestion.getCurrentSet().getAudio());
        final Uri media = Uri.parse("android.resource://" + getPackageName() +"/raw/" + Data);
        player = player.create(getBaseContext(), media);
        player = MediaPlayer.create(getApplicationContext(),media);
        player.start();
        player.setOnCompletionListener(new MediaPlayerListener());
    }

    private void disableOptionButton(){
        option_1.setClickable(false);
        option_2.setClickable(false);
        option_3.setClickable(false);
        option_4.setClickable(false);
    }

    private void enableOptionButton(){
        option_1.setClickable(true);
        option_2.setClickable(true);
        option_3.setClickable(true);
        option_4.setClickable(true);
    }

    @Override
    public void onAnswerClick(boolean answer) {
        if(QUESTION_COUNTER < QUESTION_LIMIT){
            if(answer) {
                SCORE_COUNTER++;
                AnswerHandler.rightAnswerHandler(score_text,this,SCORE_COUNTER,QUESTION_LIMIT);
                progressBarScore = findViewById(R.id.progress_bar_score);
                progressBarScore.setProgress(SCORE_COUNTER);
                progressBarScore.setMax(QUESTION_LIMIT);
            }
            animateNext();
            QUESTION_COUNTER++;
        } else {
            /**Incrementing the final score*/
            SCORE_COUNTER++;
            AnswerHandler.finalAnswerPlayer(this);

            this.gameCompleted();
        }
        enableOptionButton();
    }

    public void gameCompleted(){
        GameCompleteDialog dialog=new GameCompleteDialog(QuizActivity.this, SCORE_COUNTER);
        dialog.buildDialog();
        dialog.showDialog();
        handler.removeCallbacks(timerThread);
    }

    @Override
    public void onBackPressed() {
        stopTimer();
        finish();
        super.onBackPressed();
    }

    @Override
    public void onTimeFinish() {
        TimeCompleteDialog dialog = new TimeCompleteDialog(this);
        dialog.showDialog();
        AnswerHandler.vibrate(this);
        stopTimer();
    }

}