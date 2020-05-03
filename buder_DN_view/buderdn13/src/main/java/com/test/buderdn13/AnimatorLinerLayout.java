package com.test.buderdn13;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;


/**
 * 外层布局控件，总控内部
 */
public class AnimatorLinerLayout extends LinearLayout {

    public AnimatorLinerLayout(Context context) {
        this(context, null);
    }

    public AnimatorLinerLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimatorLinerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }

    /**
     * 这个函数在加载XML布局时自动调用，可以获取到每一个系统控件配置的布局参数，包括自定义参数。
     * 每加载一个系统控件（如Imageview），则调用一次这个函数。
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        ////从attrs所有参数里提取自定义属性值，并保持在MyLayoutParams对象里，以供“自定义包裹VIEWGROUP"使用并执行动画。
        return new AnimatorLayoutParams(getContext(), attrs);
    }

    //1.考虑到系统控件不识别自定义属性，所以我门要考虑给控件包一层帧
    //2.这里采取有父容器组件给子容器包裹一层的方式
    //3.系统是通过夹杂i 布局文件，然后调用VIEW的addView来进行加载的
    //4.那么此时我门进行偷天换日

    /**
     * 这个函数是在generateLayoutParams之后执行，在这里我们可以获取到generateLayoutParams函数返回的MyLayoutParams里的自定义属性值。
     * 然后在addview系统控件（如Imageview）之前，先创建并添加一个“自定义包裹VIEWGROUP"视图，然后将自定义属性赋给这个视图，最后在把系统控件
     * ddview到"自定义包裹VIEWGROUP"里，从而实现了在代码中为XML里的每一个系统控件外层包裹一个“自定义包裹VIEWGROUP"视图。
     * @param child
     * @param params
     */
    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        //获取自定义属性，这时考虑到自定义属性在子控件当中，
        //那么系统控件不识别自定义属性，怎么让自定义属性到这个里面来
        //来看源码

        //根据源码流程-->先调用generateLayoutParams组装XML属性参数
        //在调用addView进行添加，所以，自定义属性在generateLayoutParams中进行组装获取
        //在addView当中将具体的值进行封装
        AnimatorLayoutParams layoutParams = (AnimatorLayoutParams) params;
        AnimatorFramelayout view = new AnimatorFramelayout(child.getContext());

        if (!isDiscrollvable(layoutParams)) {
            //没有自定义属性的系统控件，我们就不需要外层包裹一个“自定义包裹VIEWGROUP"视图。直接addview即可。
            super.addView(view);
        } else {
            //有自定义属性的系统控件，我们需要外层包裹一个“自定义包裹VIEWGROUP"视图。
            view.addView(child);
            view.setmDiscrollveAlpha(layoutParams.mDiscrollveAlpha);
            view.setmDiscrollveFromBgColor(layoutParams.mDiscrollveFromBgColor);
            view.setmDiscrollveToBgColor(layoutParams.mDiscrollveToBgColor);
            view.setmDiscrollveScaleX(layoutParams.mDiscrollveScaleX);
            view.setmDiscrollveScaleX(layoutParams.mDiscrollveScaleY);
            view.setmDisCrollveTranslation(layoutParams.mDisCrollveTranslation);
            super.addView(view, params);
        }
        //至此到这一步就已经获取到了自己的自定义属性，可以进行操作了
    }

    private boolean isDiscrollvable(AnimatorLayoutParams layoutParams) {
        return layoutParams.mDiscrollveAlpha ||
                layoutParams.mDiscrollveScaleX ||
                layoutParams.mDiscrollveScaleY ||
                layoutParams.mDisCrollveTranslation != -1 ||
                (layoutParams.mDiscrollveFromBgColor != -1 &&
                        layoutParams.mDiscrollveToBgColor != -1);
    }

    /**
     * 自定义LayoutParams
     * 获取自定义属性
     */
    private class AnimatorLayoutParams extends LinearLayout.LayoutParams {

        private boolean mDiscrollveAlpha;
        private boolean mDiscrollveScaleX;
        private boolean mDiscrollveScaleY;
        private int mDisCrollveTranslation;
        private int mDiscrollveFromBgColor;
        private int mDiscrollveToBgColor;

        private AnimatorLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.DiscrollView_LayoutParams);
            //没有传属性过来,给默认值FALSE
            mDiscrollveAlpha = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_alpha, false);
            mDiscrollveScaleX = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleX, false);
            mDiscrollveScaleY = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleY, false);
            mDisCrollveTranslation = a.getInt(R.styleable.DiscrollView_LayoutParams_discrollve_translation, -1);
            mDiscrollveFromBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_fromBgColor, -1);
            mDiscrollveToBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_toBgColor, -1);
            a.recycle();
        }
    }
}



