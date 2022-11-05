package ru.ivanmurzin.falloutdungeon.lib.game.object;

import ru.ivanmurzin.falloutdungeon.lib.GameObject;

public class Door extends GameObject {
    private DoorState state = DoorState.Closed;

    public Door(int x, int y) {
        super(x, y);
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
