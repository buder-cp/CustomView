package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.Utils;

public class Practice6DrawLineView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float distance = Utils.dpToPixel(50);

    {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(Utils.dpToPixel(5));
    }

    public Practice6DrawLineView(Context context) {
        super(context);
    }

    public Practice6DrawLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice6DrawLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawLine() 方法画直线

        canvas.drawLine(distance * 2, distance * 2, distance * 3, distance * 3, paint);

    }
}
