package com.test.cp5_pathmeasure;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import com.test.cp5_pathmeasure.util.Utils;

import androidx.annotation.Nullable;

public class GetSegmentView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private PathMeasure mPathMeasure;
    private Path mDstPath = new Path();
    private Float mCurAnimValue;

    //箭头动画
    private Bitmap mArrawBmp;
    private float[] pos = new float[2];
    private float[] tan = new float[2];

    public GetSegmentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mArrawBmp = Utils.changeBitmapSize(getResources(), R.drawable.arraw);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);
        Path mCirclePath = new Path();
        mCirclePath.addCircle(100, 100, 50, Path.Direction.CW);

        mPathMeasure = new PathMeasure(mCirclePath, false);

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurAnimValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(2000);
        animator.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float length = mPathMeasure.getLength();
        float stop = mCurAnimValue * length;
        float start = (float) (stop - ((0.5 - Math.abs(mCurAnimValue - 0.5)) * length));

        /**
         * 画出一个圆圈，终点动态变化
         * 想要动态画出截取到的path路线，只要不断改变终点的取值即可
         */
        mDstPath.reset();
        mPathMeasure.getSegment(0, stop, mDstPath, true);
        canvas.drawPath(mDstPath, paint);

        /**
         * 画出一个圆圈，起点、终点动态变化
         * 想要动态画出截取到的path路线，只要不断改变起点和终点的取值即可
         */
//        mDstPath.reset();
//        mPathMeasure.getSegment(start, stop, mDstPath, true);
//        canvas.drawPath(mDstPath, paint);

        /**
         * 箭头旋转、位移实现方式一：
         *
         */
//        mPathMeasure.getPosTan(stop, pos, tan);
//        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180 / Math.PI);
//        Matrix matrix = new Matrix();
//        matrix.postRotate(degrees, mArrawBmp.getWidth()/2, mArrawBmp.getHeight()/2);
//        matrix.postTranslate(pos[0] - (float) mArrawBmp.getWidth() / 2, pos[1] - (float) mArrawBmp.getHeight() / 2);
//        canvas.drawBitmap(mArrawBmp, matrix, paint);

        /**
         * 箭头旋转、位移实现方式二：
         */
        Matrix matrix2 = new Matrix();
        mPathMeasure.getMatrix(stop, matrix2, PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);
        matrix2.preTranslate(-mArrawBmp.getWidth() / 2, -mArrawBmp.getHeight() / 2);
        canvas.drawBitmap(mArrawBmp, matrix2, paint);
    }
}
