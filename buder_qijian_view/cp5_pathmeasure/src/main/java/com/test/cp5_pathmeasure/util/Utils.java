package com.test.cp5_pathmeasure.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

public class Utils {

    public static Bitmap changeBitmapSize(Resources res, int id) {
        Bitmap bitmap = BitmapFactory.decodeResource(res, id);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Log.e("width", "width:" + width);
        Log.e("height", "height:" + height);

        //设置想要的大小
        int newWidth = 30;
        int newHeight = 30;

        //计算压缩的比率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        //获取想要缩放的matrix
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        //获取新的bitmap
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        bitmap.getWidth();
        bitmap.getHeight();
        Log.e("newWidth", "newWidth" + bitmap.getWidth());
        Log.e("newHeight", "newHeight" + bitmap.getHeight());
        return bitmap;

    }
}
