package ru.ivanmurzin.falloutdungeon.lib.game.object;

import ru.ivanmurzin.falloutdungeon.controller.Logger;
import ru.ivanmurzin.falloutdungeon.lib.InteractiveGameObject;
import ru.ivanmurzin.falloutdungeon.lib.game.Field;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;

public class GameItem extends InteractiveGameObject {
    public final Item item;
    public final Field.Level level;

    public GameItem(Item item, Field.Level level, float x, float y) {
        super(x, y);
        this.item = item;
        this.level = level;
    }

    @Override
    public void action(Logger logger) {
        if (item.pick(logger)) {
            level.removeInteractive(this);
        }
    }
}
