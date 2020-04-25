package com.test.buderdn18;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class MenuPutLayout extends RelativeLayout {

    MenuContentLayout contentLayout;
    MenuBgView bgView;

    public MenuPutLayout(MenuContentLayout contentLayout) {
        this(contentLayout.getContext());
        init(contentLayout);
    }

    public MenuPutLayout(Context context) {
        this(context, null);
    }

    public MenuPutLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuPutLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(MenuContentLayout contentLayout) {
        this.contentLayout = contentLayout;
        //把content的   宽高转移到外面RelatiLayout
        setLayoutParams(contentLayout.getLayoutParams());

        //背景先添加进去
        bgView = new MenuBgView(getContext());
        bgView.setColor(contentLayout.getBackground());
        addView(bgView, 0, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        //把contentView  的背景颜色取出来    设置给 bgView   把contentView弄成透明
        contentLayout.setBackgroundColor(Color.TRANSPARENT);
        addView(contentLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    /**
     * 传递偏移Y
     * @param y
     * @param slideOffset
     */
    public void setTouchY(float y, float slideOffset) {
        bgView.setTouchY(y,slideOffset);
        contentLayout.setTouchY(y, slideOffset);
    }
}
