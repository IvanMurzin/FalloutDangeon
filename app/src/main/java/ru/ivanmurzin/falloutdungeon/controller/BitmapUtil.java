package ru.ivanmurzin.falloutdungeon.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtil {
    public static Bitmap getScaledBitmap(Context context, int width, int height, int id) {
        return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), id), 200, 200, false);
    }
}
