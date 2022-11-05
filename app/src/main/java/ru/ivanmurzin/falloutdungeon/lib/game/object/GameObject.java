package ru.ivanmurzin.falloutdungeon.lib.game.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public abstract class GameObject {
    public final Bitmap bitmap;
    public int x;
    public int y;

    public GameObject(int x, int y, Bitmap bitmap) {
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
    }

    public void draw(Canvas canvas, GameDisplay display) {
        canvas.drawBitmap(bitmap, display.offsetX(x), display.offsetY(y), null);
    }
}
