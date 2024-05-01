package com.learn_alphabet.activities.quiz;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.core.content.res.ResourcesCompat;

import com.learn_alphabet.R;
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
import com.learn_alphabet.databinding.ActivityQuizBinding;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends BaseFragmentActivity implements View.OnClickListener, AnswerListener, OnTimeCompleteListener {

    private final Handler handler = new Handler();
    public static int timer;
    private final OnTimeCompleteListener timeComplete = this;
    private int QUESTION_COUNTER = 1;
    private int SCORE_COUNTER = 0;
    public static int QUESTION_LIMIT;
    String Data;
    private MediaPlayer player = null;
    private CurrentQuestion currentQuestion;
    UtilityFile utility;
    ActivityQuizBinding root;

    private final Runnable timerThread = new Runnable() {
        @Override
        public void run() {
            if (timer > 0) {
                root.time.setText(String.valueOf(timer));
                root.progressBar.setProgress(timer);
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
        root = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(root.getRoot());
        player = new MediaPlayer();

        root.score.setText("0/" + QUESTION_LIMIT);


        root.quizLevelOption1.setOnClickListener(this);
        root.quizLevelOption2.setOnClickListener(this);
        root.quizLevelOption3.setOnClickListener(this);
        root.quizLevelOption4.setOnClickListener(this);
        root.backbtn.setOnClickListener(this);
        root.backbtn.setOnClickListener(view -> onBackPressed());

        utility = new UtilityFile();
        refreshQuestion();
        handler.postDelayed(timerThread, 10);

        root.progressBar.setMax(timer);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void refreshQuestion() {
        List<Integer> randomOptionOrdering = new ArrayList<>();
        currentQuestion = utility.setCurrentQuestionSet(QuizQuestionHandler.questionSet);

        ImageView[] buttons = {root.quizLevelOption1, root.quizLevelOption2, root.quizLevelOption3, root.quizLevelOption4};
        int answerIndex = utility.randInt(0, 3);

        buttons[answerIndex].setImageDrawable(ResourcesCompat.getDrawable(getResources(), currentQuestion.getCurrentSet().getAnsImage(), getTheme()));
        buttons[answerIndex].setTag(currentQuestion.getCurrentSet().getId());
        root.quizQuestion.setText(currentQuestion.getCurrentSet().getQuestion());
        root.imageAudio.setImageResource(R.drawable.speaker);
        root.imageAudio.setOnClickListener(view -> StartPlay());

        buttons[randomOrder(randomOptionOrdering, answerIndex)].setImageDrawable(ResourcesCompat.getDrawable(getResources(), currentQuestion.getOption_1().getAnsImage(), getTheme()));
        buttons[randomOrder(randomOptionOrdering, answerIndex)].setImageDrawable(ResourcesCompat.getDrawable(getResources(), currentQuestion.getOption_2().getAnsImage(), getTheme()));
        buttons[randomOrder(randomOptionOrdering, answerIndex)].setImageDrawable(ResourcesCompat.getDrawable(getResources(), currentQuestion.getOption_3().getAnsImage(), getTheme()));
    }

    @Override
    public void onClick(View v) {
        disableOptionButton();
        AnswerFinder finder;

        if (v.getId() == R.id.quiz_level_option_1) {
            finder = new AnswerFinder(root.quizLevelOption1, currentQuestion, this);
            finder.getAnswer();
            return;
        }

        if (v.getId() == R.id.quiz_level_option_2) {
            finder = new AnswerFinder(root.quizLevelOption2, currentQuestion, this);
            finder.getAnswer();
            return;
        }

        if (v.getId() == R.id.quiz_level_option_3) {
            finder = new AnswerFinder(root.quizLevelOption3, currentQuestion, this);
            finder.getAnswer();
            return;
        }

        if (v.getId() == R.id.quiz_level_option_4) {
            finder = new AnswerFinder(root.quizLevelOption4, currentQuestion, this);
            finder.getAnswer();
        }
    }

    public void animateNext() {
        Animation anim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.right_to_left);
        root.subjectAnimate1.startAnimation(anim);
        anim.setDuration(200);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                stopTimer();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation anim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.left_to_right);
                anim.setDuration(200);
                refreshQuestion();
                root.subjectAnimate1.startAnimation(anim);
                startTimer();
            }
        });
    }

    public void startTimer() {
        handler.postDelayed(timerThread, 100);
    }

    public void stopTimer() {
        handler.removeCallbacks(timerThread);
    }

    public int randomOrder(List<Integer> rand, int currentAnswerIndex) {
        while (true) {
            int index = new UtilityFile().randInt(0, 3);
            if (index != currentAnswerIndex) {
                if (!isInserted(rand, index)) {
                    rand.add(index);
                    return index;
                }
            }
        }
    }

    public boolean isInserted(List<Integer> inserted, int currentIndex) {
        for (Integer inte : inserted) {
            if (inte == currentIndex) {
                return true;
            }
        }
        return false;
    }

    private void StartPlay() {
        player = new MediaPlayer();
        Data = String.valueOf(currentQuestion.getCurrentSet().getAudio());
        final Uri media = Uri.parse("android.resource://" + getPackageName() + "/raw/" + Data);
        player = MediaPlayer.create(getBaseContext(), media);
        player = MediaPlayer.create(getApplicationContext(), media);
        player.start();
        player.setOnCompletionListener(new MediaPlayerListener());
    }

    private void disableOptionButton() {
        root.quizLevelOption1.setClickable(false);
        root.quizLevelOption2.setClickable(false);
        root.quizLevelOption3.setClickable(false);
        root.quizLevelOption4.setClickable(false);
    }

    private void enableOptionButton() {
        root.quizLevelOption1.setClickable(true);
        root.quizLevelOption2.setClickable(true);
        root.quizLevelOption3.setClickable(true);
        root.quizLevelOption4.setClickable(true);
    }

    @Override
    public void onAnswerClick(boolean answer) {
        if (QUESTION_COUNTER < QUESTION_LIMIT) {
            if (answer) {
                SCORE_COUNTER++;
                AnswerHandler.rightAnswerHandler(root.score, this, SCORE_COUNTER, QUESTION_LIMIT);
                root.progressBarScore.setProgress(SCORE_COUNTER);
                root.progressBarScore.setMax(QUESTION_LIMIT);
            }
            animateNext();
            QUESTION_COUNTER++;
        } else {
            SCORE_COUNTER++;
            AnswerHandler.finalAnswerPlayer(this);

            this.gameCompleted();
        }
        enableOptionButton();
    }

    public void gameCompleted() {
        GameCompleteDialog dialog = new GameCompleteDialog(QuizActivity.this, SCORE_COUNTER);
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