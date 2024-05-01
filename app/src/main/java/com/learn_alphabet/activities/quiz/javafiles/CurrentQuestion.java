package com.learn_alphabet.activities.quiz.javafiles;

import com.learn_alphabet.activities.quiz.utils.QuestionSet;

public class CurrentQuestion {

    QuestionSet currentSet;
    QuestionSet option_1;
    QuestionSet option_2;
    QuestionSet option_3;
    public QuestionSet getCurrentSet() {
        return currentSet;
    }
    public void setCurrentSet(QuestionSet currentSet) {
        this.currentSet = currentSet;
    }
    public QuestionSet getOption_1() {
        return option_1;
    }
    public void setOption_1(QuestionSet option_1) {
        this.option_1 = option_1;
    }
    public QuestionSet getOption_2() {
        return option_2;
    }
    public void setOption_2(QuestionSet option_2) {
        this.option_2 = option_2;
    }
    public QuestionSet getOption_3() {
        return option_3;
    }
    public void setOption_3(QuestionSet option_3) {
        this.option_3 = option_3;
    }

}
