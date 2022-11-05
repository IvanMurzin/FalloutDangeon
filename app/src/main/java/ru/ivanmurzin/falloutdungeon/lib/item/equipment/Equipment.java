package ru.ivanmurzin.falloutdungeon.lib.item.equipment;

import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Special;

public abstract class Equipment extends Item {
    protected final Special enchantScale;

    public Equipment(String name, Special enchantScale) {
        super(name);
        this.enchantScale = enchantScale;
    }
}
