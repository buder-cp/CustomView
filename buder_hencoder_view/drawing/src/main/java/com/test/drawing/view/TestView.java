package com.test.drawing.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TestView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 在整个layout过程结束之后，实际尺寸改变了，才会调用一次，如果发现尺寸没有改变那么就不会被调用
     * 好处是：不会被过多调用，提高应用性能
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        path.reset();
        path.addRect(getWidth() / 2 - 150, getHeight() / 2 - 300,
                getWidth() / 2 + 150, getHeight() / 2, Path.Direction.CCW);

        path.addCircle(getWidth() / 2, getHeight() / 2, 150, Path.Direction.CCW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //https://blog.csdn.net/a1003434346/article/details/85218112
        path.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(path, paint);
    }
}
