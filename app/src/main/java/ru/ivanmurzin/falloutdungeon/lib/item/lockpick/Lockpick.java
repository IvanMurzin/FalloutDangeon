package ru.ivanmurzin.falloutdungeon.lib.item.lockpick;

import ru.ivanmurzin.falloutdungeon.controller.Logger;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;

public class Lockpick extends Item {
    public final int count;

    public Lockpick(int count) {
        super("Свзяка из " + count + " отмычек");
        this.count = count;
    }

    @Override
    public void pick(Logger logger) {
        Hero.instance.addLockpicks(count);
        logger.notifyInfo("Отмычек в запасе: " + Hero.instance.getLockpicks());
    }
}
