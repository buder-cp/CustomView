package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.Utils;

public class Practice11PieChartView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    RectF rectF = new RectF(200, 100, 800, 500);

    float distance = Utils.dpToPixel(15);

    float round = Utils.dpToPixel(10);

    {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(Utils.dpToPixel(5));
    }

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        paint.setColor(Color.DKGRAY);
        canvas.drawArc(rectF, -65, 65, true, paint);

        paint.setColor(Color.GREEN);
        canvas.drawArc(rectF, 10, 20, true, paint);

        paint.setColor(Color.BLACK);
        canvas.drawArc(rectF, 35, 40, true, paint);

        paint.setColor(Color.LTGRAY);
        canvas.drawArc(rectF, 80, 60, true, paint);

        paint.setColor(Color.BLUE);
        canvas.drawArc(rectF, 145, 35, true, paint);


        canvas.save();
        canvas.translate((float) Math.cos(Math.toRadians(190 + 50)) * distance,
                (float) Math.sin(Math.toRadians(190 + 50)) * distance);
        paint.setColor(Color.RED);
        canvas.drawArc(rectF, 190, 100, true, paint);
        canvas.restore();
    }
}
