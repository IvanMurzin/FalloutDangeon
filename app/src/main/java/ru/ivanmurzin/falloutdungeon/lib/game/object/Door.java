package ru.ivanmurzin.falloutdungeon.lib.game.object;

import ru.ivanmurzin.falloutdungeon.controller.Logger;
import ru.ivanmurzin.falloutdungeon.lib.InteractiveGameObject;
import ru.ivanmurzin.falloutdungeon.lib.game.Field;

public class Door extends InteractiveGameObject {
    private final Field field;
    private DoorState state = DoorState.Closed;


    public Door(Field field, int x, int y) {
        super(x, y);
        this.field = field;
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
        if (state != DoorState.Open) {
            logger.notifyError(field.getLevel().getUnits().size() + "");
        } else {
            field.nextLevel();
        }
    }
}
