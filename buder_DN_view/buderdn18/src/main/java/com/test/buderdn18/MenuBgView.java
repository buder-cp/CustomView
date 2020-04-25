package com.test.buderdn18;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MenuBgView extends View {

    Paint paint;
    Path path;

    public MenuBgView(Context context) {
        this(context, null);
    }

    public MenuBgView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuBgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        path = new Path();
    }

    public void setTouchY(float y, float percent) {
        path.reset();

        float width = getWidth() * percent;

        float offsetY = getHeight() / 9;
        float beginX = 0;
        float beginY = -offsetY;

        float endX = 0;
        float endY = getHeight() + offsetY;

        float controllX = width * 3 / 2;
        float controllY = y;

        path.lineTo(beginX, beginY);
        path.quadTo(controllX, controllY, endX, endY);

        path.close();
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    public void setColor(Drawable color) {
        if (color instanceof ColorDrawable) {
            ColorDrawable colorDrawable= (ColorDrawable) color;
            paint.setColor(colorDrawable.getColor());
        }
    }
}
