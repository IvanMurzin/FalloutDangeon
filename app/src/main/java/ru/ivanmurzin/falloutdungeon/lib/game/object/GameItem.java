package ru.ivanmurzin.falloutdungeon.lib.game.object;

import ru.ivanmurzin.falloutdungeon.controller.Logger;
import ru.ivanmurzin.falloutdungeon.lib.InteractiveGameObject;
import ru.ivanmurzin.falloutdungeon.lib.game.Level;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;

public class GameItem extends InteractiveGameObject {
    public final Item item;
    public final Level level;

    public GameItem(Item item, Level level, float x, float y) {
        super(x, y);
        this.item = item;
        this.level = level;
    }

    @Override
    public void action(Logger logger) {
        item.pick(logger);
        level.removeInteractive(this);
    }
}
