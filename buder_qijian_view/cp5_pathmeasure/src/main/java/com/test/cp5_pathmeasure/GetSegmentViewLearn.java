package com.test.cp5_pathmeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GetSegmentViewLearn extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path originPath = new Path();
    Path dstPath = new Path();
    PathMeasure measure;

    public GetSegmentViewLearn(Context context) {
        super(context);
    }

    public GetSegmentViewLearn(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);

        originPath.addRect(-50, -50, 50, 50, Path.Direction.CCW);
        measure = new PathMeasure(originPath, false);
    }

    /**
     *
     * getSegment函数使用：用于截取整个Path中的某个片段，并将截取后的Path保存到参数dstPath中
     *
     *
     * @param canvas
     */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(100, 100);
        measure.getSegment(0, 150, dstPath, true);
        canvas.drawPath(dstPath, paint);
    }
}
