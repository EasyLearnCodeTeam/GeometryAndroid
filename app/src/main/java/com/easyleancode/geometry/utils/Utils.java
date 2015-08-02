package com.easyleancode.geometry.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.opengl.GLUtils;
import android.os.Build;

import com.easyleancode.geometry.R;

import javax.microedition.khronos.opengles.GL10;

public class Utils {

    public static Drawable getDrawable(Context context, int resId) {
        if (Build.VERSION.SDK_INT < 21) {
            return context.getResources().getDrawable(resId);
        } else {
            return context.getDrawable(resId);
        }
    }

    public static ColorStateList getColor(Context context, int resId) {
        return ColorStateList.valueOf(context.getResources().getColor(resId));
    }

    public static void draw(Context context, GL10 gl, String str) {
        // Create an empty, mutable bitmap
        Bitmap bitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        bitmap.eraseColor(0);
        Drawable background = context.getResources().getDrawable(R.mipmap.app_icon);
        background.setBounds(0, 0, 256, 256);
        background.draw(canvas);
        // Draw the text
        Paint textPaint = new Paint();
        textPaint.setTextSize(40);
        textPaint.setAntiAlias(true);
        textPaint.setARGB(0xff, 0xff, 0x00, 0x00);
        canvas.drawText(str, 0, 0, textPaint);

        int[] textures = new int[1];
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glEnable(GL10.GL_BLEND);
        gl.glGenTextures(1, textures, 0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

//Create Nearest Filtered Texture
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

//Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

//Use the Android GLUtils to specify a two-dimensional texture image from our bitmap
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

//Clean up
        bitmap.recycle();
        gl.glDisable(GL10.GL_BLEND);
        gl.glDisable(GL10.GL_TEXTURE_2D);
    }
}
