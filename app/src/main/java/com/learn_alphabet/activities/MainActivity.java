package com.learn_alphabet.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.learn_alphabet.R;
import com.learn_alphabet.activities.drawingboard.activity.DrawBoardActivity;
import com.learn_alphabet.activities.quiz.utils.QuizQuestionHandler;
import com.learn_alphabet.adapter.LearnAdapter;
import com.learn_alphabet.databinding.ActivityMainBinding;
import com.learn_alphabet.utils.SharedPreference;


public class MainActivity extends AppCompatActivity implements OnClickListener {
    private static final String TAG = "MainActivity";
    private AudioManager audio;
    public static SharedPreference sharedPreference;
    private long mLastClickTime = 0;
    MediaPlayer playerr;
    public Dialog myDialog;
    private String musicStatus;
    private LearnAdapter learnAdapter;
    ActivityMainBinding root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(1);
        setContentView(root.getRoot());


        this.audio = (AudioManager) getSystemService(AUDIO_SERVICE);
        // init dialog
        myDialog = new Dialog(this);

        this.playerr = MediaPlayer.create(this, R.raw.intro_01);
        this.playerr.start();
        this.playerr.setLooping(true);


        QuizQuestionHandler.populateList();


        learnAdapter = new LearnAdapter(this);
        root.rcv.setAdapter(learnAdapter);

        root.btnmusic.setOnClickListener(this);
        root.btnPrivacy.setOnClickListener(this);
        root.btnShare.setOnClickListener(this);
        root.btnRate.setOnClickListener(this);
        root.btnExit.setOnClickListener(this);

        root.drawBtn.setOnClickListener(this);
        root.quizBtn.setOnClickListener(this);
        root.drawTextBtn.setOnClickListener(this);
        root.drawNumberBtn.setOnClickListener(this);
    }


    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        long elapsedTime = SystemClock.elapsedRealtime() - this.mLastClickTime;
        if (elapsedTime >= 1000) {
            this.mLastClickTime = SystemClock.elapsedRealtime();
            int id = view.getId();
            Intent intent;

            if (id == R.id.drawTextBtn) {
                // Intent drawingid = new Intent(MainActivity.this, DrawingActivity.class);
                // drawingid.putExtra("type", DrawingResourcePool.DRAWING_ALPHABET);
                // startActivity(drawingid);
                // playerr.pause();
            } else if (id == R.id.drawNumberBtn) {
                // Intent drawingNumberId = new Intent(MainActivity.this, DrawingActivity.class);
                // drawingNumberId.putExtra("type", DrawingResourcePool.NUMBER);
                // startActivity(drawingNumberId);
                // playerr.pause();
            } else if (id == R.id.drawBtn) {
                 requestPermissionWrite();
                 Intent GoToDrawing = new Intent(this, DrawBoardActivity.class);
                 startActivity(GoToDrawing);
            } else if (id == R.id.quizBtn) {
                // Intent i = new Intent(this, QuizActivity.class);
                // QuizQuestionHandler.populateList();
                // QuizActivity.timer = 200;
                // QuizActivity.QUESTION_LIMIT = 45;
                // GameCompleteDialog.Score = 30;
                // startActivity(i);
                // onAddLodded();
                // adShow();
            } else {
                if (id == R.id.btnmusic) {
                    if (playerr.isPlaying()) {
                        this.playerr.pause();
                        root.btnmusic.setImageResource(R.drawable.music_off);
                    } else {
                        this.playerr.start();
                        this.playerr.setLooping(true);
                        root.btnmusic.setImageResource(R.drawable.music_on);
                    }
                } else if (id == R.id.btn_privacy) {
                    // Intent btn_privacy = new Intent(this, PrivacyWebview.class);
                    // startActivity(btn_privacy);
                } else if (id == R.id.btn_share) {
                    // ShowShareDialog();
                } else if (id == R.id.btn_rate) {
                    // ShowRateDialog();
                } else if (id == R.id.btn_exit) {
                    // ShowExitDialog();
                }
            }
        }
    }

    ///////////////////////////////////////////////////////////////// Permission //////////////////////////////////////////////////////
    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1001) {
            int length = strArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                String str = strArr[i2];
                if (iArr[i2] == -1) {
                    if (!shouldShowRequestPermissionRationale(str)) {
                        sharedPreference.saveStoragePermissionNever(this, true);
                    } else {
                        Toast.makeText(this, getString(R.string.msg_save_picture), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    public void requestPermissionWrite() {
        if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED) {
            Log.v("KidsPreschool", "Permission is granted");
        } else {
            Log.v("KidsPreschool", "Permission is revoked");
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1001);
        }
    }


///////////////////////////////////////////////////////////////// //////////////////////////////////////////////////////


    protected void onUserLeaveHint() {
        this.playerr.pause();
        super.onUserLeaveHint();
    }

    protected void onPause() {
        super.onPause();
        if (!((PowerManager) getSystemService(POWER_SERVICE)).isScreenOn()) {
            this.playerr.pause();
        }
    }
////////////////////////////////////////////////////// Exit Dialog ////////////////////////////////////////

//    public void ShowExitDialog() {
//        // init dialog
//        myDialog = new Dialog(this);
//
//        TextView txtclose;
//        Button btnExit;
//        Button btnstay;
//        myDialog.setContentView(R.layout.exit_dialog);
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
//        txtclose.setText("X");
//
//        btnExit = (Button) myDialog.findViewById(R.id.btnExit);
//        btnExit.setOnClickListener(v -> {
//            playerr.stop();
//            playerr.release();
//            myDialog.dismiss();
//            finishAffinity();
//
//        });
//
//        btnstay = (Button) myDialog.findViewById(R.id.btnstay);
//        btnstay.setOnClickListener(v -> myDialog.dismiss());
//
//        txtclose.setOnClickListener(v -> myDialog.dismiss());
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        myDialog.show();
//    }

    ////////////////////////////////////////////////////// Rate Dialog ////////////////////////////////////////

//    public void ShowRateDialog() {
//        // init dialog
//        myDialog = new Dialog(this);
//
//        TextView txtclose;
//        Button btnLater;
//        Button btnrate;
//        myDialog.setContentView(R.layout.rate_dialog);
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
//        txtclose.setText("X");
//
//        btnrate = (Button) myDialog.findViewById(R.id.btnrate);
//        btnrate.setOnClickListener(v -> {
//            Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
//            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
//            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
//                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
//                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//            try {
//                startActivity(goToMarket);
//            } catch (ActivityNotFoundException e) {
//                startActivity(new Intent(Intent.ACTION_VIEW,
//                        Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
//            }
//            //  playerr.stop();
//            //  playerr.release();
//            myDialog.dismiss();
//        });
//
//        btnLater = (Button) myDialog.findViewById(R.id.btnLater);
//        btnLater.setOnClickListener(v -> myDialog.dismiss());
//
//        txtclose.setOnClickListener(v -> myDialog.dismiss());
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        myDialog.show();
//    }
    ////////////////////////////////////////////////////// Rate Dialog ////////////////////////////////////////

//    public void ShowShareDialog() {
//        // init dialog
//        myDialog = new Dialog(this);
//
//        TextView txtclose;
//        Button btnLater;
//        Button btnshare;
//        myDialog.setContentView(R.layout.share_dialog);
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
//        txtclose.setText("X");
//
//        btnshare = (Button) myDialog.findViewById(R.id.btnshare);
//        btnshare.setOnClickListener(v -> {
//            Intent myIntent = new Intent(Intent.ACTION_SEND);
//            myIntent.setType("text/plain");
//            String body = "Let your kids learn english basics via many funny activities with our : " + getString(R.string.app_name) + " app" + ", You can download it from google play via link : " + "http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName();
//            myIntent.putExtra(Intent.EXTRA_TEXT, body);
//            startActivity(Intent.createChooser(myIntent, "Share Using"));
//
//            myDialog.dismiss();
//        });
//
//        btnLater = (Button) myDialog.findViewById(R.id.btnLater);
//        btnLater.setOnClickListener(v -> myDialog.dismiss());
//
//        txtclose.setOnClickListener(v -> myDialog.dismiss());
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        myDialog.show();
//    }
}
