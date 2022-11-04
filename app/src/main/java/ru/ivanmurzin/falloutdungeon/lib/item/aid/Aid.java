package ru.ivanmurzin.falloutdungeon.lib.item.aid;

import ru.ivanmurzin.falloutdungeon.lib.item.Item;

public abstract class Aid extends Item {
    public final int weight;

    public Aid(String name, int weight) {
        super(name);
        this.weight = weight;
    }
}
