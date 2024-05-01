package com.learn_alphabet.activities.drawingboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.learn_alphabet.R;
//import com.companyname.english_for_kids_preschool.drawingboard.utility.AppUtilities;
import com.learn_alphabet.activities.drawingboard.utility.DrawingView;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.LoadAdError;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.initialization.InitializationStatus;
//import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
//import com.google.android.gms.ads.interstitial.InterstitialAd;
//import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;


public class DrawBoardActivity extends BaseActivity implements View.OnClickListener {
    private Activity mActivity;
    private Context mContext;
//    private InterstitialAd mInterstitialAd;
    private DrawingView mDrawingView;
    private float mSmallBrush, mMediumBrush, mLargeBrush;
    private ImageButton btn_cancel, mBtnCurrPaint, mBtnBrushSize, mBtnEraser, mBtnNewPaint, mBtnSave;
    private boolean mIsInterstitialShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_board);

//        // init ads
//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//                // createCustominterstitial();
//                // ShowAds();
//                onAddLodded();
//                // adShow();
//
//            }
//        });
//        onAddLodded();


        btn_cancel = (ImageButton) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //new button
                AlertDialog.Builder exitDialog = new AlertDialog.Builder(DrawBoardActivity.this);
                exitDialog.setTitle("Are you sure !!");
                exitDialog.setMessage("Do you want to exit from Draw Board !");
                exitDialog.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        dialog.dismiss();
                    }
                });
                exitDialog.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                exitDialog.show();


            }
        });

        initVar();
        initView();
    }

    private void initVar() {
        mActivity = DrawBoardActivity.this;
        mContext = mActivity.getApplicationContext();

    }


    private void initView() {



        // show full-screen ads
      //  AdsUtilities.getInstance(mContext).showFullScreenAd();

        mDrawingView = (DrawingView) findViewById(R.id.drawing_view);
        LinearLayout paintLayout = (LinearLayout) findViewById(R.id.lyt_paint_colors);
        mBtnCurrPaint = (ImageButton) paintLayout.getChildAt(0);
        mBtnCurrPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));

        mSmallBrush = getResources().getInteger(R.integer.small_size);
        mMediumBrush = getResources().getInteger(R.integer.medium_size);
        mLargeBrush = getResources().getInteger(R.integer.large_size);

        mBtnBrushSize = (ImageButton) findViewById(R.id.btn_brush_size);
        mBtnBrushSize.setOnClickListener(this);
        mDrawingView.setBrushSize(mMediumBrush);

        mBtnEraser = (ImageButton) findViewById(R.id.btn_eraser);
        mBtnEraser.setOnClickListener(this);

        mBtnNewPaint = (ImageButton) findViewById(R.id.btn_new_paint);
        mBtnNewPaint.setOnClickListener(this);

        mBtnSave = (ImageButton) findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(this);



        ImageButton colorImageButton1 = (ImageButton) findViewById(R.id.button1);
        colorImageButton1.setOnClickListener(this);
        ImageButton colorImageButton2 = (ImageButton) findViewById(R.id.button2);
        colorImageButton2.setOnClickListener(this);
        ImageButton colorImageButton3 = (ImageButton) findViewById(R.id.button3);
        colorImageButton3.setOnClickListener(this);
        ImageButton colorImageButton4 = (ImageButton) findViewById(R.id.button4);
        colorImageButton4.setOnClickListener(this);
        ImageButton colorImageButton5 = (ImageButton) findViewById(R.id.button5);
        colorImageButton5.setOnClickListener(this);
        ImageButton colorImageButton6 = (ImageButton) findViewById(R.id.button6);
        colorImageButton6.setOnClickListener(this);
        ImageButton colorImageButton7 = (ImageButton) findViewById(R.id.button7);
        colorImageButton7.setOnClickListener(this);
        ImageButton colorImageButton8 = (ImageButton) findViewById(R.id.button8);
        colorImageButton8.setOnClickListener(this);
        ImageButton colorImageButton9 = (ImageButton) findViewById(R.id.button9);
        colorImageButton9.setOnClickListener(this);
        ImageButton colorImageButton10 = (ImageButton) findViewById(R.id.button10);
        colorImageButton10.setOnClickListener(this);
        ImageButton colorImageButton11 = (ImageButton) findViewById(R.id.button11);
        colorImageButton11.setOnClickListener(this);
    }

    public void paintClicked(View view) {
        //use chosen color
        if (view != mBtnCurrPaint) {
            //update color
            ImageButton imgView = (ImageButton) view;
            String color = view.getTag().toString();
            mDrawingView.setColor(color);
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
            mBtnCurrPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            mBtnCurrPaint = (ImageButton) view;
            mDrawingView.setErase(false);
            mDrawingView.setBrushSize(mDrawingView.getLastBrushSize());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.button1:
//            case R.id.button2:
//            case R.id.button3:
//            case R.id.button4:
//            case R.id.button5:
//            case R.id.button6:
//            case R.id.button7:
//            case R.id.button8:
//            case R.id.button9:
//            case R.id.button10:
//            case R.id.button11:
//
//
//                paintClicked(view);
//                break;
            default:
                break;
        }
        if (view.getId() == R.id.btn_brush_size) {
            //draw button clicked
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle(getResources().getString(R.string.brush_size));
            brushDialog.setContentView(R.layout.layout_brush_chooser);

            ImageButton smallBtn = (ImageButton) brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawingView.setBrushSize(mSmallBrush);
                    mDrawingView.setLastBrushSize(mSmallBrush);
                    mDrawingView.setErase(false);
                    brushDialog.dismiss();
                }
            });
            ImageButton mediumBtn = (ImageButton) brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawingView.setBrushSize(mMediumBrush);
                    mDrawingView.setLastBrushSize(mMediumBrush);
                    mDrawingView.setErase(false);
                    brushDialog.dismiss();
                }
            });

            ImageButton largeBtn = (ImageButton) brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawingView.setBrushSize(mLargeBrush);
                    mDrawingView.setLastBrushSize(mLargeBrush);
                    mDrawingView.setErase(false);
                    brushDialog.dismiss();
                }
            });
            brushDialog.show();
        } else if (view.getId() == R.id.btn_eraser) {
            //switch to erase - choose size
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle(getResources().getString(R.string.easer_size));
            brushDialog.setContentView(R.layout.layout_brush_chooser);

            ImageButton smallBtn = (ImageButton) brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawingView.setErase(true);
                    mDrawingView.setBrushSize(mSmallBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton mediumBtn = (ImageButton) brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawingView.setErase(true);
                    mDrawingView.setBrushSize(mMediumBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton largeBtn = (ImageButton) brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawingView.setErase(true);
                    mDrawingView.setBrushSize(mLargeBrush);
                    brushDialog.dismiss();
                }
            });

            brushDialog.show();
        } else if (view.getId() == R.id.btn_new_paint) {
            //new button
            AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
            newDialog.setTitle(getResources().getString(R.string.new_drawing));
            newDialog.setMessage(getResources().getString(R.string.start_new_drawing));
            newDialog.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    mDrawingView.startNew();
                    dialog.dismiss();
                }
            });
            newDialog.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            newDialog.show();


        } else if (view.getId() == R.id.btn_save) {
//            mDrawingView.setDrawingCacheEnabled(true);
//            Bitmap bitmap = Bitmap.createBitmap(mDrawingView.getDrawingCache());
//            mDrawingView.destroyDrawingCache();
//            AppUtilities.downloadFile(mContext, mActivity, bitmap);
//            onAddLodded();
//            adShow();
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // show full-screen ads
       // AdsUtilities.getInstance(mContext).showFullScreenAd();
        finish();
    }

    // loading Interstitial Ads


    public void onAddLodded() {
//        //mInterstitialAd.loadAd(new AdRequest.Builder().build());
//        AdRequest adRequest = new AdRequest.Builder().build();
//
//        InterstitialAd.load(this,getString(R.string.admob_interstitial_id), adRequest, new InterstitialAdLoadCallback() {
//            @Override
//            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
//                // The mInterstitialAd reference will be null until
//                // an ad is loaded.
//                mInterstitialAd = interstitialAd;
//                Log.i("TAG", "onAdLoaded");
//                //  LoadInter();
//            }
//
//            @Override
//            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                // Handle the error
//                Log.i("TAG", loadAdError.getMessage());
//                mInterstitialAd = null;
//            }
//        });
    }

    public void adShow() {
//        // Show the ad if it's ready. Otherwise toast and restart the game.
//        if (mInterstitialAd != null) {
//            mInterstitialAd.show(this);
//            onAddLodded();
//        } else {
//          //  Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
//
//        }
//
    }
}
