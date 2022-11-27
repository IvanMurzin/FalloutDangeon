package ru.ivanmurzin.falloutdungeon.lib.game;

import ru.ivanmurzin.falloutdungeon.lib.GameObject;

public abstract class MovingGameObject extends GameObject {
    public MovingGameObject(float x, float y) {
        super(x, y);
    }

    public abstract void move();
}
