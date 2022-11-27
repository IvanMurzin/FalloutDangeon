package ru.ivanmurzin.falloutdungeon.controller.object;

import android.graphics.Canvas;

import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public interface Drawer {

    void draw(Canvas canvas, GameDisplay display);

}
