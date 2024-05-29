package com.learn_alphabet.activities.drawingboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.core.content.res.ResourcesCompat;

import com.learn_alphabet.R;
import com.learn_alphabet.activities.drawingboard.utility.DrawingView;
import com.learn_alphabet.utils.AppUtilities;

import java.util.Arrays;
import java.util.List;

public class DrawBoardActivity extends BaseActivity implements View.OnClickListener {
    private DrawingView mDrawingView;
    private float mSmallBrush, mMediumBrush, mLargeBrush;
    private ImageButton btn_cancel, mBtnCurrPaint, mBtnBrushSize, mBtnEraser, mBtnNewPaint, mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_board);

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(view -> {

            //new button
            AlertDialog.Builder exitDialog = new AlertDialog.Builder(DrawBoardActivity.this);
            exitDialog.setTitle("Are you sure !!");
            exitDialog.setMessage("Do you want to exit from Draw Board !");
            exitDialog.setPositiveButton(getResources().getString(R.string.yes), (dialog, which) -> {
                finish();
                dialog.dismiss();
            });
            exitDialog.setNegativeButton(getResources().getString(R.string.cancel), (dialog, which) -> dialog.cancel());
            exitDialog.show();


        });

        initView();
    }

    private void initView() {

        mDrawingView = findViewById(R.id.drawing_view);
        LinearLayout paintLayout = findViewById(R.id.lyt_paint_colors);
        mBtnCurrPaint = (ImageButton) paintLayout.getChildAt(0);
        mBtnCurrPaint.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.paint_pressed, null));

        mSmallBrush = getResources().getInteger(R.integer.small_size);
        mMediumBrush = getResources().getInteger(R.integer.medium_size);
        mLargeBrush = getResources().getInteger(R.integer.large_size);

        mBtnBrushSize = findViewById(R.id.btn_brush_size);
        mBtnBrushSize.setOnClickListener(this);
        mDrawingView.setBrushSize(mMediumBrush);

        mBtnEraser = findViewById(R.id.btn_eraser);
        mBtnEraser.setOnClickListener(this);

        mBtnNewPaint = findViewById(R.id.btn_new_paint);
        mBtnNewPaint.setOnClickListener(this);

        mBtnSave = findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(this);


        ImageButton colorImageButton1 = findViewById(R.id.button1);
        colorImageButton1.setOnClickListener(this);
        ImageButton colorImageButton2 = findViewById(R.id.button2);
        colorImageButton2.setOnClickListener(this);
        ImageButton colorImageButton3 = findViewById(R.id.button3);
        colorImageButton3.setOnClickListener(this);
        ImageButton colorImageButton4 = findViewById(R.id.button4);
        colorImageButton4.setOnClickListener(this);
        ImageButton colorImageButton5 = findViewById(R.id.button5);
        colorImageButton5.setOnClickListener(this);
        ImageButton colorImageButton6 = findViewById(R.id.button6);
        colorImageButton6.setOnClickListener(this);
        ImageButton colorImageButton7 = findViewById(R.id.button7);
        colorImageButton7.setOnClickListener(this);
        ImageButton colorImageButton8 = findViewById(R.id.button8);
        colorImageButton8.setOnClickListener(this);
        ImageButton colorImageButton9 = findViewById(R.id.button9);
        colorImageButton9.setOnClickListener(this);
        ImageButton colorImageButton10 = findViewById(R.id.button10);
        colorImageButton10.setOnClickListener(this);
        ImageButton colorImageButton11 = findViewById(R.id.button11);
        colorImageButton11.setOnClickListener(this);
    }

    public void paintClicked(View view) {
        //use chosen color
        if (view != mBtnCurrPaint) {
            //update color
            ImageButton imgView = (ImageButton) view;
            String color = view.getTag().toString();
            mDrawingView.setColor(color);
            imgView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.paint_pressed, null));
            mBtnCurrPaint.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.paint, null));
            mBtnCurrPaint = (ImageButton) view;
            mDrawingView.setErase(false);
            mDrawingView.setBrushSize(mDrawingView.getLastBrushSize());
        }
    }

    @Override
    public void onClick(View view) {
        List<Integer> buttonIds = Arrays.asList(
                R.id.button1,
                R.id.button2,
                R.id.button3,
                R.id.button4,
                R.id.button5,
                R.id.button6,
                R.id.button7,
                R.id.button8,
                R.id.button9,
                R.id.button10,
                R.id.button11
        );

        if (buttonIds.contains(view.getId())) {
            paintClicked(view);
        }

        if (view.getId() == R.id.btn_brush_size) {
            //draw button clicked
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle(getResources().getString(R.string.brush_size));
            brushDialog.setContentView(R.layout.layout_brush_chooser);

            ImageButton smallBtn = brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(v -> {
                mDrawingView.setBrushSize(mSmallBrush);
                mDrawingView.setLastBrushSize(mSmallBrush);
                mDrawingView.setErase(false);
                brushDialog.dismiss();
            });
            ImageButton mediumBtn = brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(v -> {
                mDrawingView.setBrushSize(mMediumBrush);
                mDrawingView.setLastBrushSize(mMediumBrush);
                mDrawingView.setErase(false);
                brushDialog.dismiss();
            });

            ImageButton largeBtn = brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(v -> {
                mDrawingView.setBrushSize(mLargeBrush);
                mDrawingView.setLastBrushSize(mLargeBrush);
                mDrawingView.setErase(false);
                brushDialog.dismiss();
            });
            brushDialog.show();
        } else if (view.getId() == R.id.btn_eraser) {
            //switch to erase - choose size
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle(getResources().getString(R.string.easer_size));
            brushDialog.setContentView(R.layout.layout_brush_chooser);

            ImageButton smallBtn = brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(v -> {
                mDrawingView.setErase(true);
                mDrawingView.setBrushSize(mSmallBrush);
                brushDialog.dismiss();
            });
            ImageButton mediumBtn = brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(v -> {
                mDrawingView.setErase(true);
                mDrawingView.setBrushSize(mMediumBrush);
                brushDialog.dismiss();
            });
            ImageButton largeBtn = brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(v -> {
                mDrawingView.setErase(true);
                mDrawingView.setBrushSize(mLargeBrush);
                brushDialog.dismiss();
            });

            brushDialog.show();
        } else if (view.getId() == R.id.btn_new_paint) {
            //new button
            AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
            newDialog.setTitle(getResources().getString(R.string.new_drawing));
            newDialog.setMessage(getResources().getString(R.string.start_new_drawing));
            newDialog.setPositiveButton(getResources().getString(R.string.yes), (dialog, which) -> {
                mDrawingView.startNew();
                dialog.dismiss();
            });
            newDialog.setNegativeButton(getResources().getString(R.string.cancel), (dialog, which) -> dialog.cancel());
            newDialog.show();


        } else if (view.getId() == R.id.btn_save) {
            mDrawingView.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(mDrawingView.getDrawingCache());
            mDrawingView.destroyDrawingCache();
            AppUtilities.downloadFile(this, bitmap);
        }
    }
}
