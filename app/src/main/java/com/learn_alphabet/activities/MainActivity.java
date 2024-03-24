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
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.learn_alphabet.R;
import com.learn_alphabet.activities.quiz.utils.QuizQuestionHandler;
import com.learn_alphabet.utils.SharedPreference;


public class MainActivity extends AppCompatActivity implements OnClickListener {
    private static final String TAG = "MainActivity";
    private AudioManager audio;
    ImageView btnmusic = null;
    ImageView btn_privacy = null;
    ImageView btn_share = null;
    ImageView btn_rate = null;
    ImageView btn_exit = null;
    //  ImageView colorgbtn = null;
    ImageView drawbtn = null;
    ImageView Quizbtn = null;
    public static SharedPreference sharedPreference;
    ImageView drawingBtn = null;
    ImageView drawingNumberBtn = null;
    ImageView icon1Btn = null;
    ImageView icon2Btn = null;
    ImageView icon3Btn = null;
    ImageView icon4Btn = null;
    ImageView icon5Btn = null;
    ImageView icon6Btn = null;
    ImageView ColorsActivitybtn = null;
    ImageView BodyPartsActivitybtn = null;
    ImageView ProfessionsActivitybtn = null;
    ImageView VehiclesActivitybtn = null;
    ImageView ShapesActivitybtn = null;
    private long mLastClickTime = 0;
    MediaPlayer playerr;
    public Dialog myDialog;
    private String musicStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.audio = (AudioManager) getSystemService(AUDIO_SERVICE);


        // init dialog
        myDialog = new Dialog(this);

        this.playerr = MediaPlayer.create(this, R.raw.intro_01);
        this.playerr.start();
        this.playerr.setLooping(true);

        requestWindowFeature(1);
        setContentView(R.layout.activity_main);

        QuizQuestionHandler.populateList();

        this.btnmusic = (ImageView) findViewById(R.id.btnmusic);
        this.btn_privacy = (ImageView) findViewById(R.id.btn_privacy);
        this.btn_share = (ImageView) findViewById(R.id.btn_share);
        this.btn_rate = (ImageView) findViewById(R.id.btn_rate);
        this.btn_exit = (ImageView) findViewById(R.id.btn_exit);
        this.drawbtn = (ImageView) findViewById(R.id.drawbtn);
        this.Quizbtn = (ImageView) findViewById(R.id.Quizbtn);

        // Set OnclickListner for main buttons

        this.btnmusic.setOnClickListener(this);
        this.btn_privacy.setOnClickListener(this);
        this.btn_share.setOnClickListener(this);
        this.btn_rate.setOnClickListener(this);
        this.btn_exit.setOnClickListener(this);
        // this.colorgbtn.setOnClickListener(this);
        this.drawbtn.setOnClickListener(this);
        this.Quizbtn.setOnClickListener(this);

        // Init Game Categories Buttons

        this.icon1Btn = (ImageView) findViewById(R.id.icon1Id);
        this.icon2Btn = (ImageView) findViewById(R.id.icon2Id);
        this.icon3Btn = (ImageView) findViewById(R.id.icon3Id);
        this.icon4Btn = (ImageView) findViewById(R.id.icon4Id);
        this.icon5Btn = (ImageView) findViewById(R.id.icon5Id);
        this.icon6Btn = (ImageView) findViewById(R.id.icon6Id);
        this.drawingBtn = (ImageView) findViewById(R.id.drawingId);
        this.drawingNumberBtn = (ImageView) findViewById(R.id.drawingNumberId);
        this.ColorsActivitybtn = (ImageView) findViewById(R.id.ColorsActivitybtn);
        this.BodyPartsActivitybtn = (ImageView) findViewById(R.id.BodyPartsActivitybtn);
        this.ProfessionsActivitybtn = (ImageView) findViewById(R.id.ProfessionsActivitybtn);
        this.ShapesActivitybtn = (ImageView) findViewById(R.id.ShapesActivitybtn);
        this.VehiclesActivitybtn = (ImageView) findViewById(R.id.VehiclesActivitybtn);

        // Set OnclickListner For Game Categories Buttons

        this.icon1Btn.setOnClickListener(this);
        this.icon2Btn.setOnClickListener(this);
        this.icon3Btn.setOnClickListener(this);
        this.icon4Btn.setOnClickListener(this);
        this.icon5Btn.setOnClickListener(this);
        this.icon6Btn.setOnClickListener(this);
        this.drawingBtn.setOnClickListener(this);
        this.drawingNumberBtn.setOnClickListener(this);
        this.ColorsActivitybtn.setOnClickListener(this);
        this.BodyPartsActivitybtn.setOnClickListener(this);
        this.ProfessionsActivitybtn.setOnClickListener(this);
        this.ShapesActivitybtn.setOnClickListener(this);
        this.VehiclesActivitybtn.setOnClickListener(this);

        // Set Animations For Game Categories Buttons

        this.icon1Btn.startAnimation(AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.rotation
        ));

        this.icon2Btn.startAnimation(AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.rotation
        ));

        this.icon3Btn.startAnimation(AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.rotation
        ));

        this.icon4Btn.startAnimation(AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.rotation
        ));

        this.icon5Btn.startAnimation(AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.rotation
        ));

        this.icon6Btn.startAnimation(AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.rotation
        ));

        this.ColorsActivitybtn.startAnimation(AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.rotation
        ));

        this.BodyPartsActivitybtn.startAnimation(AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.rotation
        ));

        this.ProfessionsActivitybtn.startAnimation(AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.rotation
        ));

        this.VehiclesActivitybtn.startAnimation(AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.rotation
        ));

        this.ShapesActivitybtn.startAnimation(AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.rotation
        ));


        // END Of Animations For Game Categories Buttons


    }


    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        long elapsedTime = SystemClock.elapsedRealtime() - this.mLastClickTime;
        if (elapsedTime >= 1000) {
            this.mLastClickTime = SystemClock.elapsedRealtime();
            int id = view.getId();
            Intent intent;
            if (id == R.id.drawingId) {
                // Intent drawingid = new Intent(MainActivity.this, DrawingActivity.class);
                // drawingid.putExtra("type", DrawingResourcePool.DRAWING_ALPHABET);
                // startActivity(drawingid);
                // playerr.pause();
            } else if (id == R.id.drawingNumberId) {
                // Intent drawingNumberId = new Intent(MainActivity.this, DrawingActivity.class);
                // drawingNumberId.putExtra("type", DrawingResourcePool.NUMBER);
                // startActivity(drawingNumberId);
                // playerr.pause();
            } else {
                if (id == R.id.icon1Id) {
                    // Intent icon1Id = new Intent(MainActivity.this, AlphaActivity.class);
                    // icon1Id.putExtra("type", DrawingResourcePool.ICON1);
                    // startActivity(icon1Id);
                    // playerr.pause();
                } else if (id == R.id.icon2Id) {
                    // Intent icon2Id = new Intent(MainActivity.this, NumActivity.class);
                    // icon2Id.putExtra("type", DrawingResourcePool.ICON2);
                    // startActivity(icon2Id);
                    // playerr.pause();
                } else if (id == R.id.icon3Id) {
                    // Intent icon3Id = new Intent(MainActivity.this, FruitsActivity.class);
                    // icon3Id.putExtra("type", DrawingResourcePool.ICON3);
                    // startActivity(icon3Id);
                    // playerr.pause();
                } else if (id == R.id.icon4Id) {
                    // Intent icon4Id = new Intent(MainActivity.this, AnimalActivity.class);
                    // icon4Id.putExtra("type", DrawingResourcePool.ICON4);
                    // startActivity(icon4Id);
                    // playerr.stop();
                } else if (id == R.id.icon5Id) {
                    // Intent icon5Id = new Intent(MainActivity.this, DaysActivity.class);
                    // icon5Id.putExtra("type", DrawingResourcePool.ICON5);
                    // startActivity(icon5Id);
                    // playerr.pause();
                } else if (id == R.id.icon6Id) {
                    // Intent icon6Id = new Intent(MainActivity.this, MonthsActivity.class);
                    // icon6Id.putExtra("type", DrawingResourcePool.ICON6);
                    // startActivity(icon6Id);
                    // playerr.pause();
                } else if (id == R.id.ColorsActivitybtn) {
                    // Intent ColorsActivitybtn = new Intent(MainActivity.this, ColorsActivity.class);
                    // ColorsActivitybtn.putExtra("type", DrawingResourcePool.COLORS);
                    // startActivity(ColorsActivitybtn);
                    // playerr.pause();
                } else if (id == R.id.BodyPartsActivitybtn) {
                    // Intent BodyPartsActivitybtn = new Intent(MainActivity.this, BodyPartsActivity.class);
                    // BodyPartsActivitybtn.putExtra("type", DrawingResourcePool.BODY);
                    // startActivity(BodyPartsActivitybtn);
                    // playerr.pause();
                } else if (id == R.id.ProfessionsActivitybtn) {
                    // Intent ProfessionsActivitybtn = new Intent(MainActivity.this, ProfessionsActivity.class);
                    // ProfessionsActivitybtn.putExtra("type", DrawingResourcePool.PROFESSIONS);
                    // startActivity(ProfessionsActivitybtn);
                    // playerr.pause();
                } else if (id == R.id.ShapesActivitybtn) {
                    // Intent ShapesActivitybtn = new Intent(MainActivity.this, ShapesActivity.class);
                    // ShapesActivitybtn.putExtra("type", DrawingResourcePool.SHAPES);
                    // startActivity(ShapesActivitybtn);
                    // playerr.pause();
                } else if (id == R.id.VehiclesActivitybtn) {
                    // Intent VehiclesActivitybtn = new Intent(MainActivity.this, VehiclesActivity.class);
                    // VehiclesActivitybtn.putExtra("type", DrawingResourcePool.VEHICLES);
                    // startActivity(VehiclesActivitybtn);
                    // playerr.pause();
                } else if (id == R.id.btnmusic) {
                    if (playerr.isPlaying()) {
                        this.playerr.pause();
                        btnmusic.setImageResource(R.drawable.music_off);
                    } else {
                        this.playerr.start();
                        this.playerr.setLooping(true);
                        btnmusic.setImageResource(R.drawable.music_on);
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
                } else if (id == R.id.drawbtn) {
                    // requestPermissionWrite();
                    // Intent GoToDrawing = new Intent(this, DrawBoardActivity.class);
                    // startActivity(GoToDrawing);
                } else if (id == R.id.Quizbtn) {
                    // Intent i = new Intent(this, QuizActivity.class);
                    // QuizQuestionHandler.populateList();
                    // QuizActivity.timer = 200;
                    // QuizActivity.QUESTION_LIMIT = 45;
                    // GameCompleteDialog.Score = 30;
                    // startActivity(i);
                    // onAddLodded();
                    // adShow();
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
