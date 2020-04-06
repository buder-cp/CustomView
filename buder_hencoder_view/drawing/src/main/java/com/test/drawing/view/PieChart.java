package com.test.drawing.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.test.drawing.Utils;

import androidx.annotation.Nullable;

public class PieChart extends View {

    private static final float RADIUS = Utils.dp2px(150);
    private static final int OUTLENGTH = (int) Utils.dp2px(20);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int[] angles = {60, 100, 120, 80};
    int[] colors = {Color.parseColor("#2979FF"), Color.parseColor("#C2185B"),
            Color.parseColor("#009688"), Color.parseColor("#FF8F00")};


    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 画出四种颜色的圆形，圆形是由四个扇形组成的
         */
        int currentAngle = 0;
        for (int i = 0; i < colors.length; i++) {
            paint.setColor(colors[i]);
            canvas.save();
            /**
             * 根据sin cos算出向外偏移的距离，
             * 注意：这里的角度应该是该偏移扇形之前的扇形角度和再加上该扇形角度的一半
             */
            if (i == 2) {
                canvas.translate((float) Math.cos(Math.toRadians(currentAngle + angles[i] / 2)) * OUTLENGTH,
                        (float) Math.sin(Math.toRadians(currentAngle + angles[i] / 2)) * OUTLENGTH);
            }

            canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                    currentAngle, angles[i], true, paint);
            canvas.restore();
            currentAngle += angles[i];
        }

    }
}
