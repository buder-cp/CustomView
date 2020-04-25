package com.test.buderdn18;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MenuContentLayout extends LinearLayout {

    private float maxTranslationX;

    public MenuContentLayout(Context context) {
        this(context, null);
    }

    public MenuContentLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuContentLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);
        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.SideBar);
            maxTranslationX = array.getDimension(R.styleable.SideBar_maxTranslationX, 0);
            array.recycle();
        }
    }

    public void setTouchY(float y, float slideOffset) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            apply(child, y, slideOffset);
        }
    }

    private void apply(View child, float y, float slideOffset) {

        float translationX = 0;

        int centerY = child.getTop() + child.getHeight() / 2;
        float distance = Math.abs(y - centerY);
        float scale = distance / getHeight() * 3;
        translationX = maxTranslationX - scale * maxTranslationX;
        child.setTranslationX(translationX);

    }

}
