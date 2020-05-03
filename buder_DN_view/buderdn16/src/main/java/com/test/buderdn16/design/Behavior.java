package com.test.buderdn16.design;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Behavior {
    public Behavior(Context context, AttributeSet set) {

    }

    public void onTouchMove(View parent, View child, MotionEvent event, float x, float y, float oldx, float oldy) {

    }

    /**
     * 布局绘制完成
     *
     * @param parent
     * @param child
     */
    public void onLayoutFinish(View parent, View child) {

    }

    //将所有的事件  类型kaolv齐全
    public void onSizeChanged(View parent, View child, int w, int h, int oldw, int oldh) {

    }

    public boolean onTouchEvent(BehaviorCoordinatorLayout parent, View child, MotionEvent ev) {
        return false;
    }

    public boolean onLayoutChild(BehaviorCoordinatorLayout parent, View child, int layoutDirection) {
        return false;
    }

    //当前实现的NestedScrolling机制，在进行滚动的时候，这里必须是为true
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.d("barry", "onStartNestedScroll");
        return true;
    }

    public void onStopNestedScroll(View child) {
        Log.d("barry", "onStopNestedScroll");
    }

    public void onNestedScrollAccepted(View child, View target, int axes) {
        Log.d("barry", "onNestedScrollAccepted");
    }

    public void onNestedScroll(View scrollView, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    }

    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
    }

    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }
}
