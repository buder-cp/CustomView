package com.test.buderdn16;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.test.buderdn16.design.Behavior;


public class ImageBehavior extends Behavior {
    private static final String TAG = "barry";

    public ImageBehavior(Context context, AttributeSet set) {
        super(context, set);
    }

    private int maxHeight = 400;
    private int originHeight;


    @Override
    public void onLayoutFinish(View parent, View child) {
        super.onLayoutFinish(parent, child);
        if (originHeight == 0) {
            originHeight = child.getHeight();
        }
    }

    //滚动方法就在这里进行缩放

    // 参数dxConsumed:表示target已经消费的x方向的距离
    // 参数dyConsumed:表示target已经消费的y方向的距离
    // 参数dxUnconsumed:表示x方向剩下的滑动距离
    // 参数dyUnconsumed:表示y方向剩下的滑动距离
    @Override
    public void onNestedScroll(View scrollView, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i(TAG, "onNestedScroll: " + scrollView.getScrollY() + "   dyConsumed  " + dyConsumed);
        if (scrollView.getScrollY() > 0) {
            ViewGroup.LayoutParams parmas = target.getLayoutParams();
            Log.i(TAG, "onNestedScroll: parmas.height  " + parmas.height + "  originHeight  " + originHeight);
            parmas.height = parmas.height - Math.abs(dyConsumed);
            if (parmas.height < originHeight) {
                parmas.height = originHeight;
            }
            target.setLayoutParams(parmas);
        } else if (scrollView.getScrollY() == 0) {
            ViewGroup.LayoutParams params = target.getLayoutParams();
            params.height = params.height + Math.abs(dyUnconsumed);
            if (params.height >= maxHeight) {
                params.height = maxHeight;
            }
            target.setLayoutParams(params);
        }
    }
}
