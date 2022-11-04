package ru.ivanmurzin.falloutdungeon.lib.game.object;

import android.graphics.Bitmap;

public class Door extends GameObject {
    private DoorState state = DoorState.Closed;

    public Door(int x, int y, Bitmap bitmap) {
        super(x, y, bitmap);
    }

    public void open() {
        state = DoorState.Open;
    }

    public void close() {
        state = DoorState.Closed;
    }

    public DoorState getState() {
        return state;
    }
}
