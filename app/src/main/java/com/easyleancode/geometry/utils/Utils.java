package com.easyleancode.geometry.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

public class Utils {

    public static Drawable getDrawable(Context context, int resId) {
        if (Build.VERSION.SDK_INT < 21) {
            return context.getResources().getDrawable(resId);
        } else {
            return context.getDrawable(resId);
        }
    }
}
