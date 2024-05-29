package com.learn_alphabet.activities.quiz.javafiles;

import android.app.Activity;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.learn_alphabet.activities.quiz.QuizActivity;
import com.learn_alphabet.activities.quiz.interfaces.AnswerListener;

public class AnswerFinder implements Animation.AnimationListener {

    private CurrentQuestion c_ques;
    private ImageView fromImgBtn;
    private Object tag;
    private AnswerListener answerListner;
    private Activity act;
    private boolean isRight=false;

    public AnswerFinder(ImageView v, CurrentQuestion ques, Activity a) {
        this.fromImgBtn = v;
        this.tag = fromImgBtn.getTag();
        this.c_ques = ques;
        answerListner = (QuizActivity)a;
        this.act = a;
    }
    public void getAnswer(){
        int currentAnsId=c_ques.getCurrentSet().getId();
        /**Find whether the answer is correct or not */
        //Playing Media

        if(tag != null){
            if((Integer)tag == currentAnsId){
                isRight = true;
                AnswerHandler.rightAnswerPlayer(act);
            }else{
                AnswerHandler.wrongAnswerPlayer(act);
            }
        }

        QuizAnswerFlipAnimation flipAnimation = new QuizAnswerFlipAnimation(act,isRight,fromImgBtn, fromImgBtn);
        flipAnimation.setAnimationListener(this);
        fromImgBtn.startAnimation(flipAnimation);

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        answerListner.onAnswerClick(isRight);
    }
    @Override
    public void onAnimationRepeat(Animation animation) {}
    @Override
    public void onAnimationStart(Animation animation) {}


}
