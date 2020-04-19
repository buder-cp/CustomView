package com.test.cp5_pathmeasure;


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class AliPayView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Path mCirclePath = new Path();
    Path mCircleDstPath = new Path();
    PathMeasure mMeasure;

    float mFraction = 0;

    public AliPayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        int mCentX = 100;
        int mCentY = 100;
        int mRadius = 50;
        //先画一个圆形O
        mCirclePath.addCircle(mCentX, mCentY, mRadius, Path.Direction.CW);
        //再将path移动到画对勾的地方画√
        mCirclePath.moveTo(mCentX - mRadius / 2, mCentY);
        mCirclePath.lineTo(mCentX, mCentY + mRadius / 2);
        mCirclePath.lineTo(mCentX + mRadius / 2, mCentY - mRadius / 3);

        mMeasure = new PathMeasure(mCirclePath, false);

        ValueAnimator animator = ValueAnimator.ofFloat(0, 2);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mFraction = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(4000);
        animator.start();
    }


    boolean mNext = false;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float length = mMeasure.getLength();
        if (mFraction < 1) {
            float stop = length * mFraction;
            mMeasure.getSegment(0, stop, mCircleDstPath, true);
        } else {
            if (!mNext) {
                mNext = true;
                mMeasure.getSegment(0, length, mCircleDstPath, true);
                mMeasure.nextContour();
            }
            float stop = length * (mFraction - 1);
            mMeasure.getSegment(0, stop, mCircleDstPath, true);
        }
        canvas.drawPath(mCircleDstPath, mPaint);
    }
}
