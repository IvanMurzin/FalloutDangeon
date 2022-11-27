package ru.ivanmurzin.falloutdungeon.controller.object;

import android.graphics.Canvas;

import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public abstract class DrawController {

    public abstract void draw(Canvas canvas, GameDisplay display);

}
