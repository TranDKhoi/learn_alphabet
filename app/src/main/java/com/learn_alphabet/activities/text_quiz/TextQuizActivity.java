package com.learn_alphabet.activities.text_quiz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.learn_alphabet.R;
import com.learn_alphabet.activities.text_quiz.utils.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class TextQuizActivity extends Activity implements View.OnTouchListener, View.OnDragListener {
    public static Context contextstat;
    static int hearts;
    static int score;
    LinearLayout answers_linearLayout22;
    ImageView backToMain;
    String currentA;
    String currentQ;
    DisplayMetrics displayMetrics;
    ImageView imgQuestion;
    String[] perfect_position_array;
    LinearLayout placeholder_linearLayout;
    String[] quesList;
    TextView scored;
    TextView times;
    int width;
    int qid = 0;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_text_quiz);

        this.displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(this.displayMetrics);
        this.quesList = Constant.imageQuestions;
        this.currentQ = Constant.imageQuestions[this.qid];
        this.currentA = Constant.questionsAnswers[this.qid];
        this.imgQuestion = (ImageView) findViewById(R.id.imgQuestion);
        ImageView imageView = (ImageView) findViewById(R.id.backToMain);
        this.backToMain = imageView;
        imageView.setImageResource(R.drawable.ic_baseline_arrow_back_24);
        this.backToMain.setOnClickListener(view -> TextQuizActivity.this.finish());
        this.scored = (TextView) findViewById(R.id.score);
        score = 0;
        hearts = 5;
        setHearts(5);
        this.times = (TextView) findViewById(R.id.timers);
        setQuestionView();
        this.times.setText("00:00:60");
        new CounterClass(250000L, 1000L).start();
        getSharedPreferences("PREFERENCE", 0).edit().putBoolean("firstrun", true).apply();
        getSharedPreferences("PREFERENCE", 0).edit().putBoolean("firstrunhearts", true).apply();
        contextstat = this;
    }

    public static int getMyValue() {
        return score;
    }

    public void getAnswer(String str) {
        if (str.equals("win")) {
            this.answers_linearLayout22.removeAllViews();
            this.placeholder_linearLayout.removeAllViews();
            score++;
            this.scored.setText("Score " + score);
        }
        if (this.qid < Constant.imageQuestions.length) {
            this.currentQ = Constant.imageQuestions[this.qid];
            this.currentA = Constant.questionsAnswers[this.qid];
            setQuestionView();
            return;
        }
        Intent intent = new Intent(this, WonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("score", score);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }


    public class CounterClass extends CountDownTimer {
        public CounterClass(long j, long j2) {
            super(j, j2);
        }

        @Override
        public void onFinish() {
            Intent intent2 = new Intent(TextQuizActivity.this, ResultActivity.class);
            Bundle bundle2 = new Bundle();
            bundle2.putInt("score", TextQuizActivity.score);
            intent2.putExtras(bundle2);
            TextQuizActivity.this.startActivity(intent2);
            TextQuizActivity.this.finish();
        }

        @Override
        public void onTick(long j) {
            @SuppressLint("DefaultLocale") String format = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(j), TimeUnit.MILLISECONDS.toMinutes(j) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(j)), TimeUnit.MILLISECONDS.toSeconds(j) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(j)));
            System.out.println(format);
            TextQuizActivity.this.times.setText(format);
        }
    }

    public void setHearts(int i) {
        LinearLayout linearLayout = findViewById(R.id.linear_hearts);
        linearLayout.removeAllViews();
        for (int i2 = 1; i2 <= 5; i2++) {
            ImageView imageView = new ImageView(getApplicationContext());
            if (i2 <= i) {
                imageView.setImageDrawable(getDrawable(R.drawable.hearts));
            } else {
                imageView.setImageDrawable(getDrawable(R.drawable.hearts_empty));
            }
            imageView.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
            linearLayout.addView(imageView);
        }
    }

    private void setQuestionView() {
        this.perfect_position_array = new String[this.currentA.length()];
        if (this.currentA.length() < 7) {
            this.width = this.displayMetrics.widthPixels / 7;
        } else {
            this.width = this.displayMetrics.widthPixels / this.currentA.length();
        }
        this.imgQuestion.setImageDrawable(getDrawable(getResources().getIdentifier(this.currentQ, "drawable", getPackageName())));
        this.answers_linearLayout22 = (LinearLayout) findViewById(R.id.answers_linearLayout22);
        ((LinearLayout) findViewById(R.id.answers_linearLayout)).setOnDragListener(this);
        int length = this.currentA.length();
        ImageView[] imageViewArr = new ImageView[length];
        for (int i = 0; i < this.currentA.length(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setId(i);
            imageView.setImageDrawable(getDrawable(getResources().getIdentifier(this.currentA.charAt(i) + "_letter", "drawable", getPackageName())));
            int i2 = this.width;
            imageView.setLayoutParams(new LinearLayout.LayoutParams(i2, i2));
            imageView.setOnTouchListener(this);
            imageView.setOnDragListener(this);
            imageViewArr[i] = imageView;
            this.perfect_position_array[i] = this.currentA.charAt(i) + "," + (imageView.getId() + 9999);
        }
        shuffleArray(imageViewArr);
        for (int i3 = 0; i3 < length; i3++) {
            this.answers_linearLayout22.addView(imageViewArr[i3]);
        }
        this.placeholder_linearLayout = findViewById(R.id.placeholder_linearLayout);
        for (int i4 = 0; i4 < this.currentA.length(); i4++) {
            LinearLayout linearLayout = new LinearLayout(getApplicationContext());
            linearLayout.setBackground(getDrawable(R.drawable.placeholder));
            linearLayout.setId(i4 + 9999);
            int i5 = this.width;
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(i5, i5));
            linearLayout.setOnDragListener(this);
            this.placeholder_linearLayout.addView(linearLayout);
        }
        this.qid++;

    }

    static void shuffleArray(ImageView[] imageViewArr) {
        Random random = new Random();
        for (int length = imageViewArr.length - 1; length > 0; length--) {
            int nextInt = random.nextInt(length + 1);
            ImageView imageView = imageViewArr[nextInt];
            imageViewArr[nextInt] = imageViewArr[length];
            imageViewArr[length] = imageView;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            view.getId();
            view.startDrag(null, new View.DragShadowBuilder(view), view, 0);
            return true;
        }
        return false;
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        int id = ((View) dragEvent.getLocalState()).getId();
        int action = dragEvent.getAction();
        final View view2 = (View) dragEvent.getLocalState();
        if (action == 1) {
            view2.setVisibility(View.INVISIBLE);
        } else if (action == 3) {
            if (view.getId() >= 9999 && view.getId() <= this.currentA.length() + 9999) {
                view.setBackground(getDrawable(R.drawable.placeholder));
            }
            int childCount = this.answers_linearLayout22.getChildCount();
            ViewGroup viewGroup = (ViewGroup) view2.getParent();
            List asList = Arrays.asList(this.perfect_position_array);
            if (asList.contains(this.currentA.charAt(id) + "," + view.getId()) && view.getId() >= 9999 && view.getId() <= this.currentA.length() + 9999) {
                ArrayList arrayList = new ArrayList(Arrays.asList(this.perfect_position_array));
                arrayList.remove(this.currentA.charAt(id) + "," + view.getId());
                this.perfect_position_array = (String[]) arrayList.toArray(new String[0]);
                viewGroup.removeView(view2);
                LinearLayout linearLayout = (LinearLayout) view;
                linearLayout.addView(view2);
                if (linearLayout.getId() >= 9999) {
                    view2.setOnTouchListener(null);
                    viewGroup.setOnDragListener(null);
                }
                MediaPlayer create = MediaPlayer.create(this, (int) R.raw.right);
                create.setVolume(50.0f, 50.0f);
                create.start();
            } else {
                int i = hearts - 1;
                hearts = i;
                if (i > 0) {
                    MediaPlayer create2 = MediaPlayer.create(this, (int) R.raw.wrong);
                    create2.setVolume(50.0f, 50.0f);
                    create2.start();
                    setHearts(hearts);
                } else {
                    Intent intent = new Intent(this, ResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("score", score);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
            if (childCount == 1) {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.win));
                int i2 = this.width;
                imageView.setLayoutParams(new LinearLayout.LayoutParams(i2, i2));
                imageView.setOnTouchListener(this);
                imageView.setOnDragListener(this);
                this.answers_linearLayout22.addView(imageView);
                getAnswer("win");
            }
        } else if (action == 4) {
            view2.post(() -> view2.setVisibility(View.VISIBLE));
        } else if (action == 5) {
            Log.d("perfect_position_array", "arr: " + Arrays.toString(this.perfect_position_array));
            Log.d("perfect_position_array", "arr: " + view.getId() + "");
            List asList2 = Arrays.asList(this.perfect_position_array);
            if (asList2.contains(this.currentA.charAt(id) + "," + view.getId()) && view.getId() >= 9999 && view.getId() <= this.currentA.length() + 9999) {
                view.setBackground(getDrawable(R.drawable.placeholder_true));
            } else {
                List asList3 = Arrays.asList(this.perfect_position_array);
                if (!asList3.contains(this.currentA.charAt(id) + "," + view.getId()) && view.getId() >= 9999 && view.getId() <= this.currentA.length() + 9999) {
                    view.setBackground(getDrawable(R.drawable.placeholder_wrong));
                }
            }
        } else if (action == 6 && view.getId() >= 9999 && view.getId() <= this.currentA.length() + 9999) {
            view.setBackground(getDrawable(R.drawable.placeholder));
        }
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
