package ru.ivanmurzin.falloutdungeon.lib.game.object;

import android.graphics.Bitmap;

import ru.ivanmurzin.falloutdungeon.lib.GameObject;

public class Cell extends GameObject {
    private Bitmap bitmap;

    public Cell(int x, int y) {
        super(x, y);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


}
