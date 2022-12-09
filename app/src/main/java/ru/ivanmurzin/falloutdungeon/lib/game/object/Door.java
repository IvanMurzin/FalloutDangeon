package ru.ivanmurzin.falloutdungeon.lib.game.object;

import ru.ivanmurzin.falloutdungeon.controller.Logger;
import ru.ivanmurzin.falloutdungeon.lib.InteractiveGameObject;

public class Door extends InteractiveGameObject {
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

    @Override
    public void action(Logger logger) {
        logger.notify("Дверь");
    }
}
