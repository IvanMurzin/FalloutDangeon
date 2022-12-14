package ru.ivanmurzin.falloutdungeon.lib;

import static java.lang.Math.sqrt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class GameObject {
    public float x;
    public float y;

    public GameObject(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance(float ox, float oy) {
        return sqrt((x - ox) * (x - ox) + (y - oy) * (y - oy));
    }

    @NonNull
    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof GameObject) {
            GameObject gameObject = (GameObject) obj;
            return gameObject.x == x && gameObject.y == y;
        }
        return super.equals(obj);
    }
}
