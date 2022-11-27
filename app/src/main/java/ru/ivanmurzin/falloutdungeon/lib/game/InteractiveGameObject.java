package ru.ivanmurzin.falloutdungeon.lib.game;

import ru.ivanmurzin.falloutdungeon.controller.Logger;
import ru.ivanmurzin.falloutdungeon.lib.GameObject;

public abstract class InteractiveGameObject extends GameObject {
    public InteractiveGameObject(float x, float y) {
        super(x, y);
    }

    public abstract void action(Logger logger);
}
