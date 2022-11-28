package ru.ivanmurzin.falloutdungeon.lib;

import ru.ivanmurzin.falloutdungeon.controller.Logger;

public abstract class InteractiveGameObject extends GameObject {
    public InteractiveGameObject(float x, float y) {
        super(x, y);
    }

    public abstract void action(Logger logger);
}
