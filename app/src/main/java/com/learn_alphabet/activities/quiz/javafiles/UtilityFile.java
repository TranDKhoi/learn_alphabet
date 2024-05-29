package com.learn_alphabet.activities.quiz.javafiles;

import android.util.Log;

import com.learn_alphabet.activities.quiz.utils.QuestionSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UtilityFile {

    public List<Integer> askedQuestion;

    public UtilityFile() {
        askedQuestion = new ArrayList<Integer>();
    }

    public  CurrentQuestion setCurrentQuestionSet(
            List<QuestionSet> questionList){
        CurrentQuestion c_ques = new CurrentQuestion();
        List<Integer> insertedOption = new ArrayList<Integer>();

        int currentQuestionIndex=findCurrentQuestion(questionList);
        c_ques.setCurrentSet(questionList.get(currentQuestionIndex));
        c_ques.setOption_1(insertOption(insertedOption, questionList,currentQuestionIndex));
        c_ques.setOption_2(insertOption(insertedOption, questionList,currentQuestionIndex));
        c_ques.setOption_3(insertOption(insertedOption, questionList,currentQuestionIndex));

        return c_ques;
    }

    public  int findCurrentQuestion(List<QuestionSet> questionList){
        while(true){
            int index = randInt(0, questionList.size()-1);
            if(!isAsked(index)){
                askedQuestion.add(index);
                Log.i("asked", ""+index);
                return index;
            }
        }
    }

    public  boolean isAsked(int index){
        for(Integer value:askedQuestion){
            if(value==index){
                return true;
            }
        }
        return false;
    }

    public  QuestionSet insertOption(List<Integer> inserted,List<QuestionSet> questions,int currentQuestionIndex){
        while(true){
            int index=randInt(0, questions.size()-1);
            if(index!=currentQuestionIndex){
                if(!isInserted(inserted, index)){
                    Log.i("insertOption",""+index);
                    inserted.add(index);
                    Log.i("add",""+index);
                    return questions.get(index);
                }
            }
        }
    }

    public  boolean isInserted(List<Integer> inserted,int currentIndex){
        for(Integer inte:inserted){
            if(inte==currentIndex){
                return true;
            }
        }
        return false;
    }

    public  int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

}
