package com.test.drawing.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.test.drawing.R;
import com.test.drawing.Utils;

import androidx.annotation.Nullable;

public class ImageTextView extends View {
    private static final float IMAGE_WIDTH = Utils.dp2px(100);
    private static final float IMAGE_OFFSET = Utils.dp2px(80);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
    String text = "按照泰戈尔《流萤集》的说法，你不应该去追寻或者试图证明自己的存在，因为你原本就存在，你也不应该去追寻或者试图证明自己存在的意义，因为你存在的本身就是意义，如同流萤、如同星空，如同草长莺飞，四时消长的万物，人也只是自然的一部分，你只是按照规律繁衍与更替。但是对于我来说，我没法被这样的环状逻辑所说服。在我看来，人生并没有什么意义，活着也并没有什么意义，没有意义就是没有意义，后面不用跟上一个转折，在没有意义的前提下再次赋予意义。意义这种东西，由于可以被赋予，所以因人而异，这个世界上到处存在着这样的事情，某些人觉得毫无意义，另一些人觉得意义非凡。如果在同一件事情上，意义的尺度可以被拉成无限小至无限大，而且任意的两个尺度之间不存在证伪的可能性和必要，那么，意义其实并没有什么价值。换一种说法就是，意义本身并没有什么意义，它并非是万物自带的固有属性。";
    float[] cutWidth = new float[1];

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = getAvatar((int) Utils.dp2px(100));
        paint.setTextSize(Utils.dp2px(14));
        paint.getFontMetrics(fontMetrics);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, getWidth() - IMAGE_WIDTH, IMAGE_OFFSET, paint);
        int length = text.length();
        float verticalOffset = - fontMetrics.top;
        for (int start = 0; start < length; ) {
            int maxWidth;
            float textTop = verticalOffset + fontMetrics.top;
            float textBottom = verticalOffset + fontMetrics.bottom;
            if (textTop > IMAGE_OFFSET && textTop < IMAGE_OFFSET + IMAGE_WIDTH
                    || textBottom > IMAGE_OFFSET && textBottom < IMAGE_OFFSET + IMAGE_WIDTH) {
                // 文字和图片在同一行
                maxWidth = (int) (getWidth() - IMAGE_WIDTH);
            } else {
                // 文字和图片不在同一行
                maxWidth = getWidth();
            }
            int count = paint.breakText(text, start, length, true, maxWidth, cutWidth);
            canvas.drawText(text, start, start + count, 0, verticalOffset, paint);
            start += count;
            verticalOffset += paint.getFontSpacing();
        }
    }

    Bitmap getAvatar(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.jiqimao02, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.drawable.jiqimao02, options);
    }
}
