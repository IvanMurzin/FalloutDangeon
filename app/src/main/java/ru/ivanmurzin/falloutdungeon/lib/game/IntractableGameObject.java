package ru.ivanmurzin.falloutdungeon.lib.game;

import ru.ivanmurzin.falloutdungeon.lib.GameObject;

public abstract class IntractableGameObject extends GameObject {
    public IntractableGameObject(int x, int y) {
        super(x, y);
    }

    public abstract void action();
}
