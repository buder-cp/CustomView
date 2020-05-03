package com.test.buderdn16;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.test.buderdn16.design.Behavior;


public class ToolBarBehavior extends Behavior {
    private int maxHeight = 400;

    public ToolBarBehavior(Context context, AttributeSet set) {
        super(context, set);
    }

    /**
     * 进行透明度吧变换
     */
    @Override
    public void onNestedScroll(View scrollView, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(scrollView, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (scrollView.getScrollY() <= maxHeight) {
            //改变透明度
            target.setAlpha(scrollView.getScrollY() * 1.0f / maxHeight);
            //ViewCompat.setAlpha(target,scrollView.getScrollY()*1.0f/maxHeight);
        } else if (scrollView.getScrollY() == 0) {
            target.setAlpha(0);
            //ViewCompat.setAlpha(target,0);
        }

    }

}
