package ru.ivanmurzin.falloutdungeon.controller.object.unit;

import ru.ivanmurzin.falloutdungeon.controller.object.DrawController;

public abstract class UnitController extends DrawController {
    public UnitController(int x, int y) {
        super(x, y);
    }

    public abstract void update();
}
