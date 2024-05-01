package com.learn_alphabet.activities.quiz;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;

import com.learn_alphabet.R;

@SuppressLint("NewApi")
public class BaseFragmentActivity extends FragmentActivity {

    public static Typeface mainFont;
    protected TextView title;
    protected ImageView icon;

    @Override
    protected void onCreate(Bundle arg0) {
        requestWindowFeature(1);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(arg0);
        mainFont = ResourcesCompat.getFont(this, R.font.franklin_gothic);
    }

}
