package com.learn_alphabet;

import android.app.Application;

public class GlobalvBlue extends Application {
    private int Num = 0;

    public int getNum() {
        return this.Num;
    }

    public void setNum(int i) {
        this.Num = i;
    }
}