package ru.ivanmurzin.falloutdungeon.lib.item.equipment;

import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Special;

public abstract class Equipment {
    protected final Special enchantScale;

    public Equipment(Special enchantScale) {
        this.enchantScale = enchantScale;
    }
}
