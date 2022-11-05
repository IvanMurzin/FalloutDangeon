package ru.ivanmurzin.falloutdungeon.controller.object;

import android.graphics.Canvas;

import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public abstract class DrawController {
    protected int x;

    protected int y;

    public DrawController(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void draw(Canvas canvas, GameDisplay display);

}
