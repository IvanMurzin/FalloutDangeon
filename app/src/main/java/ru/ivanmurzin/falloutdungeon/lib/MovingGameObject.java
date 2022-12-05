package ru.ivanmurzin.falloutdungeon.lib;

public abstract class MovingGameObject extends GameObject {
    protected final float speed;

    public MovingGameObject(float x, float y, float speed) {
        super(x, y);
        this.speed = speed;
    }

    public abstract void onMove();

    public float getSpeed() {
        return speed;
    }
}
