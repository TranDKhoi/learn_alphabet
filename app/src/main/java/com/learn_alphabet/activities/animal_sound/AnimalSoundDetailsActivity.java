package com.learn_alphabet.activities.animal_sound;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.learn_alphabet.R;
import com.learn_alphabet.activities.animal_sound.db.DatabaseHelper;
import com.learn_alphabet.activities.animal_sound.models.SoundModel;
import com.learn_alphabet.adapter.AnimalAdapter;

import java.util.List;


public class AnimalSoundDetailsActivity extends AppCompatActivity {
    private int TOTAL_IMAGES;
    private ImageView Play;
    private List<SoundModel> data;
    ProgressBar progressBarScore;
    private MediaPlayer songPlayer;
    private ViewPager viewPager;
    private int currentPosition = 0;
    private final Handler mHandler = new Handler();
    private final Runnable mUpdateTimeTask = new Runnable() {
        @Override
        public void run() {
            if (AnimalSoundDetailsActivity.this.songPlayer.isPlaying()) {
                AnimalSoundDetailsActivity.this.mHandler.postDelayed(this, 100L);
            }
        }
    };


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_animal_sound_detail);


        getIntent().getStringExtra("Category");
        int intExtra = getIntent().getIntExtra("id", 0);
        try {

            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            databaseHelper.openDataBase();
            this.data = databaseHelper.getAnimalSound(intExtra);
        } catch (Exception ignore) {

        }
        this.progressBarScore = findViewById(R.id.progress_bar_score);

        this.TOTAL_IMAGES = data.size();
        SetupToolbar();
        this.songPlayer = new MediaPlayer();
        this.Play = findViewById(R.id.Play);
        ImageButton prev = findViewById(R.id.Prev);
        ImageButton next = findViewById(R.id.Next);
        this.viewPager = findViewById(R.id.viewPager);
        StartPlay();
        AnimalAdapter animalAdapter = new AnimalAdapter(this, this.data);
        this.viewPager.setAdapter(animalAdapter);
        EditPgView();
        this.Play.setOnClickListener(view -> {
            AnimalSoundDetailsActivity animalSoundDetailsActivity = AnimalSoundDetailsActivity.this;
            animalSoundDetailsActivity.PlayAction(animalSoundDetailsActivity.data.get(AnimalSoundDetailsActivity.this.viewPager.getCurrentItem()).getSound_raw());
        });
        prev.setOnClickListener(view -> {
            this.currentPosition = viewPager.getCurrentItem();
            int i = this.currentPosition - 1;
            if (i < 0) {
                i = 0;
            }
            PlayAction(data.get(i).getSound_raw());
            this.viewPager.setCurrentItem(i);
        });
        next.setOnClickListener(view -> {
            currentPosition = viewPager.getCurrentItem();
            int i = currentPosition + 1;
            if (currentPosition == TOTAL_IMAGES) {
                i = 0;
            }
            PlayAction(data.get(i).getSound_raw());
            viewPager.setCurrentItem(i);
        });
    }

    private void SetupToolbar() {
        String stringExtra = getIntent().getStringExtra("category");
        String stringExtra3 = getIntent().getStringExtra("desc_eng");
        Toolbar mToolbar = findViewById(R.id.main_mainpage_toolbar);
        TextView textView = findViewById(R.id.Titlebar);
        textView.setText(stringExtra);
        TextView titlebarDescription = findViewById(R.id.TitlebarDescription);
        setSupportActionBar(mToolbar);
        titlebarDescription.setText(stringExtra3);
    }

    private void EditPgView() {
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int i) {
            }

            @Override
            public void onPageScrolled(int i, float f, int i2) {
                String valueOf = String.valueOf(AnimalSoundDetailsActivity.this.viewPager.getCurrentItem() + 1);
                AnimalSoundDetailsActivity animalSoundDetailsActivity = AnimalSoundDetailsActivity.this;
                animalSoundDetailsActivity.progressBarScore = animalSoundDetailsActivity.findViewById(R.id.progress_bar_score);
                AnimalSoundDetailsActivity.this.progressBarScore.setMax(AnimalSoundDetailsActivity.this.data.size());
                AnimalSoundDetailsActivity.this.progressBarScore.setProgress(Integer.parseInt(valueOf));
            }

            @Override
            public void onPageSelected(int i) {
                String valueOf = String.valueOf(AnimalSoundDetailsActivity.this.currentPosition + 1);
                AnimalSoundDetailsActivity animalSoundDetailsActivity = AnimalSoundDetailsActivity.this;
                animalSoundDetailsActivity.progressBarScore = animalSoundDetailsActivity.findViewById(R.id.progress_bar_score);
                AnimalSoundDetailsActivity.this.progressBarScore.setMax(AnimalSoundDetailsActivity.this.data.size());
                AnimalSoundDetailsActivity.this.progressBarScore.setProgress(Integer.parseInt(valueOf));
            }
        });
        this.viewPager.setPageMargin(100);
        this.viewPager.setPageTransformer(false, (view, f) -> {
            int measuredWidth = (AnimalSoundDetailsActivity.this.viewPager.getMeasuredWidth() - AnimalSoundDetailsActivity.this.viewPager.getPaddingLeft()) - AnimalSoundDetailsActivity.this.viewPager.getPaddingRight();
            int height = AnimalSoundDetailsActivity.this.viewPager.getHeight();
            float left = (float) (view.getLeft() - (AnimalSoundDetailsActivity.this.viewPager.getScrollX() + AnimalSoundDetailsActivity.this.viewPager.getPaddingLeft())) / measuredWidth;
            view.setAlpha(Math.abs(Math.abs(left) - 1.0f) + 0.8f);
            int i = (-height) / 100;
            if (left < -1.0f) {
                view.setScaleY(0.7f);
                view.setTranslationY(0.0f);
            } else if (left <= 1.0f) {
                view.setScaleY(1.0f);
                view.setTranslationY(i * (1.0f - Math.abs(left)));
            } else {
                view.setTranslationY(0.0f);
                view.setScaleY(0.7f);
            }
        });
    }

    private void StartPlay() {
        MediaPlayer create = MediaPlayer.create(getBaseContext(), Uri.parse("android.resource://" + getPackageName() + "/raw/" + this.data.get(this.viewPager.getCurrentItem()).getSound_raw()));
        this.songPlayer = create;
        if (create.isPlaying()) {
            this.songPlayer.pause();
            this.Play.setBackgroundResource(R.drawable.ic_play_btn_24);
            return;
        }
        this.songPlayer.start();
        this.Play.setBackgroundResource(R.drawable.ic_baseline_pause_circle_24);
        this.mHandler.post(this.mUpdateTimeTask);
    }


    public void PlayAction(String str) {
        this.songPlayer.release();
        this.Play.setBackgroundResource(R.drawable.ic_baseline_pause_circle_24);
        this.mHandler.post(this.mUpdateTimeTask);
        MediaPlayer create = MediaPlayer.create(getBaseContext(), Uri.parse("android.resource://" + getPackageName() + "/raw/" + str));
        this.songPlayer = create;
        create.start();
        this.songPlayer.setOnCompletionListener(mediaPlayer -> AnimalSoundDetailsActivity.this.Play.setBackgroundResource(R.drawable.ic_play_btn_24));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (this.songPlayer.isPlaying()) {
            this.songPlayer.stop();
        }
        finish();
    }


    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
