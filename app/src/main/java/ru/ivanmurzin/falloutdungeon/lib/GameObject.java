package ru.ivanmurzin.falloutdungeon.lib;

import static java.lang.Math.sqrt;

import androidx.annotation.NonNull;

public abstract class GameObject {
    public int x;
    public int y;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance(GameObject o) {
        return sqrt((x - o.x) * (x - o.x) + (y - o.y) * (y - o.y));
    }

    @NonNull
    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }
}
