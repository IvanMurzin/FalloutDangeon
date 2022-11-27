package ru.ivanmurzin.falloutdungeon.lib.item;

import ru.ivanmurzin.falloutdungeon.controller.Logger;

public abstract class Item {
    public final String name;

    public Item(String name) {
        this.name = name;
    }

    public void pick(Logger logger) {
        logger.notify(name);
    }
}
