package ru.ivanmurzin.falloutdungeon.lib.item.lockpick;

import ru.ivanmurzin.falloutdungeon.lib.item.Item;

public class Lockpick extends Item {
    public final int count;

    public Lockpick(int count) {
        super("Lockpick " + count);
        this.count = count;
    }
}
