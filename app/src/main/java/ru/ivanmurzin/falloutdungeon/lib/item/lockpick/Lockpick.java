package ru.ivanmurzin.falloutdungeon.lib.item.lockpick;

import ru.ivanmurzin.falloutdungeon.lib.item.Item;

public class Lockpick extends Item {
    public final int count;

    public Lockpick(String name, int count) {
        super(name);
        this.count = count;
    }
}
