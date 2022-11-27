package ru.ivanmurzin.falloutdungeon.controller;

import static java.lang.Math.sqrt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;

public class ActionController {

    private final Bitmap action;
    private final int x;
    private final int y;


    public ActionController(Context context, int width, int height) {
        action = BitmapUtil.getScaledBitmap(context, 150, 150, R.drawable.act);
        x = width - 200;
        y = height - 200;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(action, x - 75, y - 75, null);
    }

    public boolean clickOnAction(float x, float y) {
        float dx = (this.x - x) * (this.x - x);
        float dy = (this.y - y) * (this.y - y);
        return sqrt(dx + dy) < 75;
    }
}
