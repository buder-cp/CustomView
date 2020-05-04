package com.test.buderdn20;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.ListView;

public class QQHeaderScrollView extends ListView {

    private static final String TAG = "david";
    private ImageView mImageView;
    private int mImageViewHeight;//初始高度

    public void setZoomImageView(ImageView iv) {
        mImageView = iv;
    }

    public QQHeaderScrollView(Context context) {
        super(context);
    }

    public QQHeaderScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mImageViewHeight = context.getResources().getDimensionPixelSize(R.dimen.size_default_height);
    }

    public QQHeaderScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //松手时触发缩放动画
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_UP) {
            ResetAnimation resetAnimation = new ResetAnimation(mImageViewHeight);
            resetAnimation.setInterpolator(new OvershootInterpolator());
            resetAnimation.setDuration(700);
            mImageView.startAnimation(resetAnimation);
        }
        return super.onTouchEvent(ev);
    }

    public class ResetAnimation extends Animation {

        //需要偏移回去的高度
        private int extraHeight;
        private int currentHeight;

        public ResetAnimation(int targetHeight) {
            currentHeight = mImageView.getHeight();
            extraHeight = mImageView.getHeight() - targetHeight;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            mImageView.getLayoutParams().height = (int) (currentHeight - extraHeight * interpolatedTime);
            mImageView.requestLayout();
            super.applyTransformation(interpolatedTime, t);
        }
    }

    /**
     * 上滑下拉时，改变图片的高度
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        Log.i(TAG, "overScrollBy: " + deltaY);
        if (deltaY < 0) {
            //-  下拉
            mImageView.getLayoutParams().height = mImageView.getHeight() - deltaY;
            mImageView.requestLayout();
        } else {
            //+ 上拉
            mImageView.getLayoutParams().height = mImageView.getHeight() - deltaY;
            mImageView.requestLayout();
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        View header = (View) mImageView.getParent();
//        Log.i(TAG, "onSizeChanged: " + header.getTop());
//        //ListView会滑出去的高度（负数）
//        int deltaY = header.getTop();
//        //只有image的高度大于 原始的高度，那我们就缩小
//        if (mImageView.getHeight() > mImageViewHeight) {
//            mImageView.getLayoutParams().height = mImageView.getHeight() + deltaY;
//            header.layout(header.getLeft(), 0, header.getRight(), header.getHeight());
//            mImageView.requestLayout();
//        }
//        super.onSizeChanged(w, h, oldw, oldh);
//    }
}
