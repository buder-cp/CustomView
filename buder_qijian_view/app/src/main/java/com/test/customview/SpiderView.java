package com.test.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SpiderView extends View {

    //正N边形边线
    Paint edgesPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //中心发出的射线
    Paint radialLinesPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //等级线
    Paint rankPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //等级文字
    Paint rankTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    //自定义view的宽高
    float width, height;
    //战力值数据
    private double[] rankData = {0.5, 0.2, 0.8, 0.6, 0.9, 0.6, 0.2, 0.8, 0.4, 0.9, 0.1, 0.7, 0.2, 0.9};

    //战力种类
    private String[] rankText = {"击杀", "助攻", "金钱", "物理", "防御", "魔法", "装备", "血量", "魔抗", "穿甲", "综合", "装甲", "魔抗"};

    //N边形的边数
    int edges = 7;
    //根据边数求得每个顶点对应的度数
    double degrees = 360 / edges;
    //根据度数，转化为弧度
    double hudu = (Math.PI / 180) * degrees;

    {
        edgesPaint.setStyle(Paint.Style.STROKE);
        edgesPaint.setStrokeWidth(3);
        edgesPaint.setColor(Color.BLACK);

        radialLinesPaint.setStyle(Paint.Style.STROKE);
        radialLinesPaint.setStrokeWidth(2);
        radialLinesPaint.setColor(Color.BLUE);

        rankPaint.setStyle(Paint.Style.STROKE);
        rankPaint.setStrokeWidth(10);
        rankPaint.setColor(Color.RED);

        rankTextPaint.setStyle(Paint.Style.FILL);
        rankTextPaint.setStrokeWidth(1);
        rankTextPaint.setColor(Color.BLACK);
        rankTextPaint.setTextSize(50);
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
        edgesPaint.setStyle(Paint.Style.FILL);
        edgesPaint.setColor(Color.parseColor("#c3e3e5"));
        drawPolygon(canvas, 400.0F);
        edgesPaint.setColor(Color.parseColor("#85cdd4"));
        drawPolygon(canvas, 300.0F);
        edgesPaint.setColor(Color.parseColor("#48afb6"));
        drawPolygon(canvas, 200.0F);
        edgesPaint.setColor(Color.parseColor("#22737b"));
        drawPolygon(canvas, 100.0F);

        //从中心点到各个顶点的射线
        drawLines(canvas, 400);

        //画战力值区域
        drawRanks(canvas, 400);

        //画战力文字
        drawRankText(canvas, 400);
    }

    /**
     * 画战力值区域
     *
     * @param canvas
     * @param radius
     */
    private void drawRanks(Canvas canvas, float radius) {
        Path path = new Path();
        float endx, endy;
        for (int i = 0; i < edges; i++) {
            endx = (float) (width / 2 + radius * Math.sin(hudu * i) * rankData[i]);
            endy = (float) (height / 2 - radius * Math.cos(hudu * i) * rankData[i]);
            if (i == 0) {
                path.moveTo(width / 2, (float) (height / 2 - radius * 0.5));
            } else {
                path.lineTo(endx, endy);
            }
        }
        path.close();
        canvas.drawPath(path, rankPaint);
    }

    /**
     * 画战力文字
     *
     * @param canvas
     * @param radius
     */
    private void drawRankText(Canvas canvas, float radius) {
        float endx, endy;
        Rect bounds = new Rect();
        for (int i = 0; i < edges; i++) {
            rankTextPaint.getTextBounds(rankText[i], 0, rankText[i].length(), bounds);
            endx = (float) (width / 2 + radius * 1.2 * Math.sin(hudu * i) - (bounds.right - bounds.left) / 2);
            endy = (float) (height / 2 - radius * 1.1 * Math.cos(hudu * i) + (bounds.bottom - bounds.top) / 2);
            canvas.drawText(rankText[i], endx, endy, rankTextPaint);
        }
    }

    /**
     * 从中心点到各个顶点的射线
     *
     * @param canvas
     * @param radius
     */
    private void drawLines(Canvas canvas, float radius) {
        //从中心点出发
        Path path = new Path();
        path.moveTo(width / 2, height / 2);
        float endx, endy;
        for (int i = 0; i < edges; i++) {
            endx = (float) (width / 2 + radius * Math.sin(hudu * i));
            endy = (float) (height / 2 - radius * Math.cos(hudu * i));
            path.lineTo(endx, endy);
            canvas.drawPath(path, radialLinesPaint);
            //画完一条线后，重置起点在中心点，再画下一条直线
            endx = width / 2;
            endy = height / 2;
            path.moveTo(endx, endy);
        }
    }

    /**
     * 画正N边形
     *
     * @param canvas
     * @param radius
     */
    private void drawPolygon(Canvas canvas, float radius) {
        //从上面的顶点出发
        Path path = new Path();
        path.moveTo(width / 2, height / 2 - radius);

        float endx, endy;
        for (int i = 0; i < edges; i++) {
            endx = (float) (width / 2 + radius * Math.sin(hudu * i));
            endy = (float) (height / 2 - radius * Math.cos(hudu * i));
            path.lineTo(endx, endy);
        }
        path.close();
        canvas.drawPath(path, edgesPaint);
    }
}
