package ru.ivanmurzin.falloutdungeon.lib.item.aid;

import ru.ivanmurzin.falloutdungeon.controller.Logger;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;

public abstract class Aid extends Item {
    public final int weight;

    public Aid(String name, int weight) {
        super(name);
        this.weight = weight;
    }

    public abstract void use();

    @Override
    public boolean pick(Logger logger) {
        if (Hero.instance.addAid(this)) {
            logger.notifyInfo("Добавлено: " + name);
            return true;
        }
        logger.notifyError("Нет места");
        return false;
    }
}
