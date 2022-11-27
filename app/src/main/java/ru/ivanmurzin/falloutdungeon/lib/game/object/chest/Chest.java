package ru.ivanmurzin.falloutdungeon.lib.game.object.chest;

import java.util.Random;

import ru.ivanmurzin.falloutdungeon.controller.Loggable;
import ru.ivanmurzin.falloutdungeon.lib.game.IntractableGameObject;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;

public class Chest extends IntractableGameObject {
    public final Item item;
    public final int difficulty;
    public final ChestType type;
    private ChestState state = ChestState.Closed;

    public Chest(int x, int y, Item item, int difficulty, ChestType type) {
        super(x, y);
        this.item = item;
        this.difficulty = difficulty;
        this.type = type;
    }

    public ChestState getState() {
        return state;
    }

    @Override
    public void action(Loggable loggable) {
        if (state == ChestState.Closed) {
            if (new Random().nextInt(4) == 1) {
                loggable.notifyInfo("Успех!");
                state = ChestState.Opened;
            } else {
                loggable.notifyError("Отмычка сломалась");
            }
        } else {
            state = ChestState.Clear;
        }
    }
}
