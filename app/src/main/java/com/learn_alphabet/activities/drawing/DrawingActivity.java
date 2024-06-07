package com.learn_alphabet.activities.drawing;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout.LayoutParams;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.learn_alphabet.R;
import com.learn_alphabet.databinding.ActivityDrawingBinding;


public class DrawingActivity extends AppCompatActivity implements OnClickListener, OnTouchListener {
    private int currentPosition = 0;
    DrawingView dv;
    private Paint mPaint;
    private MediaPlayer mediaPlayer = null;
    LayoutParams params = null;
    ViewGroup parent = null;
    MediaPlayer player;
    private Integer position = 0;
    private int totalItem = 0;
    private String drawType = "";
    ActivityDrawingBinding root;

    public class DrawingView extends View {
        private static final float TOUCH_TOLERANCE = 4.0f;
        private Bitmap bm;
        private final Paint circlePaint = new Paint();
        private final Path circlePath = new Path();
        Context context;
        private Bitmap mBitmap;
        private final Paint mBitmapPaint = new Paint(4);
        private Canvas mCanvas;
        private final Path mPath = new Path();
        private float mX;
        private float mY;

        public DrawingView(Context context) {
            super(context);
            this.context = context;
            this.circlePaint.setAntiAlias(true);
            this.circlePaint.setColor(Color.rgb(109, 193, 27));
            this.circlePaint.setStyle(Style.STROKE);
            this.circlePaint.setStrokeJoin(Join.MITER);
            this.circlePaint.setStrokeWidth(TOUCH_TOLERANCE);
        }

        protected void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            this.mBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
            this.mCanvas = new Canvas(this.mBitmap);
            if (drawType.equals(DrawingResourcePool.DRAWING_ALPHABET)) {
                this.bm = BitmapFactory.decodeResource(getResources(), DrawingResourcePool.capitalStoke[position]);
            } else {
                this.bm = BitmapFactory.decodeResource(getResources(), DrawingResourcePool.numberStroke[position]);
            }
            this.mCanvas.drawBitmap(this.bm, new Rect(0, 0, this.bm.getWidth(), this.bm.getHeight()), new Rect(0, 0, this.mCanvas.getWidth(), this.mCanvas.getHeight()), this.mBitmapPaint);
            if (drawType.equals(DrawingResourcePool.DRAWING_ALPHABET)) {
                totalItem = DrawingResourcePool.capitalStoke.length;
                root.itemImageId.setImageResource(DrawingResourcePool.capitalStoke[currentPosition]);
                return;
            }
            totalItem = DrawingResourcePool.numberStroke.length;
            root.itemImageId.setImageResource(DrawingResourcePool.numberStroke[currentPosition]);
        }

        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, this.mBitmapPaint);
            canvas.drawPath(this.mPath, mPaint);
            canvas.drawPath(this.circlePath, this.circlePaint);
        }

        private void touch_start(float f, float f2) {
            this.mPath.reset();
            this.mPath.moveTo(f, f2);
            this.mX = f;
            this.mY = f2;
        }

        private void touch_move(float f, float f2) {
            float abs = Math.abs(f - this.mX);
            float abs2 = Math.abs(f2 - this.mY);
            if (abs >= TOUCH_TOLERANCE || abs2 >= TOUCH_TOLERANCE) {
                this.mPath.quadTo(this.mX, this.mY, (this.mX + f) / 2.0f, (this.mY + f2) / 2.0f);
                this.mX = f;
                this.mY = f2;
                this.circlePath.reset();
                this.circlePath.addCircle(this.mX, this.mY, 30.0f, Direction.CW);
            }
        }

        private void touch_up() {
            this.mPath.lineTo(this.mX, this.mY);
            this.circlePath.reset();
            this.mCanvas.drawPath(this.mPath, mPaint);
            this.mPath.reset();
        }

        @SuppressLint("ClickableViewAccessibility")
        public boolean onTouchEvent(MotionEvent motionEvent) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            switch (motionEvent.getAction()) {
                case 0:
                    touch_start(x, y);
                    invalidate();
                    break;
                case 1:
                    touch_up();
                    invalidate();
                    break;
                case 2:
                    touch_move(x, y);
                    invalidate();
                    break;
            }
            return true;
        }

        public void resetCanvas() {
            this.bm = null;
            this.mBitmap = null;
            System.gc();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        root = ActivityDrawingBinding.inflate(getLayoutInflater());
        setContentView(root.getRoot());

        this.drawType = getIntent().getStringExtra("type");
        root.nextId.setOnTouchListener(this);
        root.nextId.setOnClickListener(this);
        root.prevId.setOnClickListener(this);
        root.prevId.setOnTouchListener(this);
        root.playId.setOnClickListener(this);
        root.playId.setOnTouchListener(this);
        root.imgBack.setOnClickListener(this);
        this.player = MediaPlayer.create(this, R.raw.intro_01);
        this.player.start();
        this.player.setLooping(true);
        this.params = (LayoutParams) root.drawingViewId.getLayoutParams();
        this.dv = new DrawingView(this);
        this.dv.setLayoutParams(this.params);
        this.parent = (ViewGroup) root.drawingViewId.getParent();
        int indexOfChild = this.parent.indexOfChild(root.drawingViewId);
        this.parent.removeView(root.drawingViewId);
        this.parent.addView(this.dv, indexOfChild);
        this.totalItem = this.drawType.equals(DrawingResourcePool.DRAWING_ALPHABET) ? DrawingResourcePool.capitalStoke.length : DrawingResourcePool.numberStroke.length;
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setColor(Color.rgb(109, 193, 27));
        this.mPaint.setStyle(Style.STROKE);
        this.mPaint.setStrokeJoin(Join.ROUND);
        this.mPaint.setStrokeCap(Cap.ROUND);
        this.mPaint.setStrokeWidth(150.0f);
        updatePreviousButton();
        if (this.drawType.equals(DrawingResourcePool.DRAWING_ALPHABET)) {
            this.mediaPlayer = MediaPlayer.create(this, DrawingResourcePool.alphabetSound[this.currentPosition]);
            this.mediaPlayer.start();
        } else {
            this.mediaPlayer = MediaPlayer.create(this, DrawingResourcePool.numberSounds[this.currentPosition]);
            this.mediaPlayer.start();
        }
        if (this.drawType.equals(DrawingResourcePool.DRAWING_ALPHABET)) {
            this.totalItem = DrawingResourcePool.capitalStoke.length;
            root.itemImageId.setImageResource(DrawingResourcePool.capitalStoke[this.currentPosition]);
            return;
        }
        this.totalItem = DrawingResourcePool.numberStroke.length;
        root.itemImageId.setImageResource(DrawingResourcePool.numberStroke[this.currentPosition]);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                exitByBackKey();
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (view.getId() == R.id.nextId || view.getId() == R.id.playId || view.getId() == R.id.prevId) {
                    view.setAlpha(0.5f);
                    break;
                }
            case 1:
                if (view.getId() == R.id.nextId || view.getId() == R.id.playId || view.getId() == R.id.prevId) {
                    view.setAlpha(1.0f);
                    break;
                }
        }
        return false;

    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.playId) {
            changeStroke();
        } else if (id == R.id.prevId) {
            this.position = this.position - 1;
            this.mediaPlayer.stop();
            changeStroke();
            gotoPrevious();
        } else if (id == R.id.nextId) {
            this.position = this.position + 1;
            this.mediaPlayer.stop();
            changeStroke();
            gotoNext();
        } else if (id == R.id.imgBack) {
            exitByBackKey();
        }

    }

    private void changeStroke() {
        updateNextButton();
        updatePreviousButton();
        int indexOfChild = this.parent.indexOfChild(this.dv);
        this.dv.resetCanvas();
        this.dv = null;
        this.parent.removeViewAt(indexOfChild);
        this.dv = new DrawingView(this);
        this.dv.setLayoutParams(this.params);
        this.parent.addView(this.dv, indexOfChild);
    }

    private void gotoNext() {
        this.currentPosition++;
        updateNextButton();
        updatePreviousButton();
        if (this.currentPosition >= 0 && this.currentPosition < this.totalItem) {
            this.mediaPlayer.stop();
            this.mediaPlayer.release();
            this.mediaPlayer = null;
            mediaPlayer = MediaPlayer.create(DrawingActivity.this,
                    drawType.equals(DrawingResourcePool.DRAWING_ALPHABET)
                            ? DrawingResourcePool.alphabetSound[currentPosition]
                            : DrawingResourcePool.numberSounds[currentPosition]);
            this.mediaPlayer.start();
        }
    }

    private void gotoPrevious() {
        currentPosition--;
        updateNextButton();
        updatePreviousButton();
        if (currentPosition >= 0 && currentPosition < totalItem) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            mediaPlayer = MediaPlayer.create(DrawingActivity.this,
                    drawType.equals(DrawingResourcePool.DRAWING_ALPHABET)
                            ? DrawingResourcePool.alphabetSound[currentPosition]
                            : DrawingResourcePool.numberSounds[currentPosition]);
            mediaPlayer.start();
        }
    }

    private void updateNextButton() {
        if (this.position == this.totalItem - 1) {
            root.nextId.setAlpha(0.5f);
            root.nextId.setClickable(false);
            return;
        }
        root.nextId.setAlpha(1.0f);
        root.nextId.setClickable(true);
    }

    private void updatePreviousButton() {
        if (this.position == 0) {
            root.prevId.setAlpha(0.5f);
            root.prevId.setClickable(false);
            return;
        }
        root.prevId.setAlpha(1.0f);
        root.prevId.setClickable(true);
    }

    protected void onUserLeaveHint() {
        this.player.pause();
        this.mediaPlayer.stop();
        super.onUserLeaveHint();
    }

    protected void onPause() {
        super.onPause();
        if (!((PowerManager) getSystemService(POWER_SERVICE)).isInteractive()) {
            this.player.pause();
            this.mediaPlayer.stop();
        }
    }

    public void onRestart() {
        super.onRestart();
        this.player.start();
    }

    public void exitByBackKey() {
        if (mediaPlayer != null) {
            this.mediaPlayer.stop();
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
        this.player.stop();
        this.player.release();
        this.player = null;
        finish();
    }
}
