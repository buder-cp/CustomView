package com.test.buderdn04;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class RoundImageView_DSTIN extends View {

    private Paint mBitPaint;
    private Bitmap BmpDST, BmpSRC;

    public RoundImageView_DSTIN(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBitPaint = new Paint();
        BmpDST = BitmapFactory.decodeResource(getResources(),R.drawable.xyjy6,null);
        BmpSRC = BitmapFactory.decodeResource(getResources(),R.drawable.shade,null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(BmpDST, 0, 0, mBitPaint);
        mBitPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(BmpSRC, 0, 0, mBitPaint);

        mBitPaint.setXfermode(null);
        canvas.restoreToCount(layerId);

    }
}
