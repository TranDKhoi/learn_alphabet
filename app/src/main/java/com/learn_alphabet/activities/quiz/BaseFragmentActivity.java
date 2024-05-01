package com.learn_alphabet.activities.quiz;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

@SuppressLint("NewApi")
public class BaseFragmentActivity extends FragmentActivity {

    public static Typeface mainFont;
    protected TextView title;
    protected ImageView icon;

    @Override
    protected void onCreate(Bundle arg0) {
        requestWindowFeature(1);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        mainFont = Typeface.createFromAsset(getAssets(),
                "fonts/Franklin Gothic.otf");

    }

}
