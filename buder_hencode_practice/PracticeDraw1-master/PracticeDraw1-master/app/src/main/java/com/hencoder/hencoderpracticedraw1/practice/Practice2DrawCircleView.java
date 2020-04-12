package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.Utils;

public class Practice2DrawCircleView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    {
        paint.setColor(Color.BLACK);
    }

    public Practice2DrawCircleView(Context context) {
        super(context);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆

        canvas.drawCircle(getWidth() / 4, getHeight() / 4, Utils.dpToPixel(40), paint);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getWidth() / 4 * 3, getHeight() / 4, Utils.dpToPixel(40), paint);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getWidth() / 4, getHeight() / 4 * 3, Utils.dpToPixel(40), paint);

        paint.setStrokeWidth(Utils.dpToPixel(20));
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getWidth() / 4 * 3, getHeight() / 4 * 3, Utils.dpToPixel(20), paint);

    }
}
