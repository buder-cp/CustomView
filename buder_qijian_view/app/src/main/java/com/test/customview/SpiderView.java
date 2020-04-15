package com.test.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SpiderView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    float width;
    float height;
    //N边形的边数
    int edges = 6;
    //根据边数求得每个顶点对应的度数
    double degrees = 360 / edges;
    //根据度数，转化为弧度
    double hudu = (Math.PI / 180) * degrees;

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.BLACK);
    }

    public SpiderView(Context context) {
        super(context);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //正N边形个数
        drawPolygon(canvas, 500.0F);
        drawPolygon(canvas, 400.0F);
        drawPolygon(canvas, 300.0F);
        drawPolygon(canvas, 200.0F);
        drawPolygon(canvas, 100.0F);
    }

    private void drawPolygon(Canvas canvas, float radius) {
        Path path = new Path();
        path.moveTo(width / 2, height / 2 - radius);//从上面的顶点出发

        float endx, endy;

        for (int i = 0; i < edges; i++) {
            endx = (float) (width / 2 + radius * Math.sin(hudu * i));
            endy = (float) (height / 2 - radius * Math.cos(hudu * i));
            path.lineTo(endx, endy);
        }
        path.close();
        canvas.drawPath(path, paint);
    }
}
