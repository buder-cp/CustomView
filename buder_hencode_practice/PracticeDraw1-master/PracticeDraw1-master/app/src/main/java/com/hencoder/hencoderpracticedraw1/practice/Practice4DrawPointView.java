package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.Utils;

public class Practice4DrawPointView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float distance = Utils.dpToPixel(100);

    {
        paint.setColor(Color.BLACK);
    }

    public Practice4DrawPointView(Context context) {
        super(context);
    }

    public Practice4DrawPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice4DrawPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPoint() 方法画点
//        一个圆点，一个方点
//        圆点和方点的切换使用 paint.setStrokeCap(cap)：`ROUND` 是圆点，`BUTT` 或 `SQUARE` 是方点

        paint.setStrokeWidth(Utils.dpToPixel(20));
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(distance, distance, paint);

        paint.setStrokeWidth(Utils.dpToPixel(20));
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(distance * 2, distance, paint);

    }
}
