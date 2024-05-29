package com.learn_alphabet.activities.quiz.javafiles;

import android.app.Activity;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;

import com.learn_alphabet.R;

public class QuizAnswerFlipAnimation extends Animation {

    private Camera camera;
    private View fromView;
    private View toView;
    private float centerX;
    private float centerY;
    private boolean forward = true;

    /**
     * Creates a 3D flip animation between two views.
     *
     * @param fromView First view in the transition.
     * @param toView Second view in the transition.
     */
    Activity _c;
    private boolean _isRight;
    public QuizAnswerFlipAnimation(Activity c,boolean isRight,View fromView, View toView)
    {
        this.fromView = fromView;
        this.toView = toView;
        this._c=c;
        this._isRight=isRight;

        setDuration(500);
        setFillAfter(false);
        setInterpolator(new AccelerateDecelerateInterpolator());
    }



    public void reverse()
    {
        forward = false;
        View switchView = toView;
        toView = fromView;
        fromView = switchView;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight)
    {
        super.initialize(width, height, parentWidth, parentHeight);
        centerX = width / 2;
        centerY = height / 2;
        camera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t)
    {
        // Angle around the y-axis of the rotation at the given time
        // calculated both in radians and degrees.
        final double radians = Math.PI * interpolatedTime;
        float degrees = (float) (180.0 * radians / Math.PI);

        // Once we reach the midpoint in the animation, we need to hide the
        // source view and show the destination view. We also need to change
        // the angle by 180 degrees so that the destination does not come in
        // flipped around
        /**Determine whether the answer is correct or not
         * */
        if (interpolatedTime >= 0.5f)
        {
            degrees -= 180.f;
            fromView.setVisibility(View.GONE);
            toView.setVisibility(View.VISIBLE);
            ImageView img=(ImageView)toView;
            if(_isRight){
                img.setImageDrawable(_c.getResources().getDrawable(R.drawable.bg_right));
            }else{
                img.setImageDrawable(_c.getResources().getDrawable(R.drawable.bg_wrong));
            }
        }

        if (forward)
            degrees = -degrees; //determines direction of rotation when flip begins

        final Matrix matrix = t.getMatrix();
        camera.save();
        camera.rotateY(degrees);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
    }

}
