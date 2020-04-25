package com.test.buderdn18;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MenuDrawerLayout extends DrawerLayout implements DrawerLayout.DrawerListener {

    private MenuContentLayout menuContentLayout;
    private View contentView;
    private MenuPutLayout menuPutLayout;
    private float y;
    private float slideOffset;

    public MenuDrawerLayout(@NonNull Context context) {
        super(context);
    }

    public MenuDrawerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuDrawerLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof MenuContentLayout) {
                menuContentLayout = (MenuContentLayout) view;
            } else {
                contentView = view;
            }
        }
        removeView(menuContentLayout);
        menuPutLayout = new MenuPutLayout(menuContentLayout);
        addView(menuPutLayout);
        addDrawerListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        y = ev.getY();
        //没有打开之前 不拦截     打开之后拦不拦截  大于1  后  内容区域不再进行偏移
//        if (slideOffset < 0.8) {
//            return super.dispatchTouchEvent(ev);
//        } else {
//            //等于  1
//            menuPutLayout.setTouchY(y, slideOffset);
//        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        this.slideOffset = slideOffset;
        menuPutLayout.setTouchY(y, slideOffset);
        //针对内容区域进行破偏移
        float contentViewOffset = drawerView.getWidth() * slideOffset / 2;
        contentView.setTranslationX(contentViewOffset);
    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {
        setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN, GravityCompat.END);
    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
