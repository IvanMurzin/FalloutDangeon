package ru.ivanmurzin.falloutdungeon.lib;

public abstract class MovingGameObject extends GameObject {
    protected final float speed;
    public final boolean removeOnCollapse;

    public MovingGameObject(float x, float y, float speed, boolean removeOnCollapse) {
        super(x, y);
        this.speed = speed;
        this.removeOnCollapse = removeOnCollapse;
    }

    public abstract void move();
}
