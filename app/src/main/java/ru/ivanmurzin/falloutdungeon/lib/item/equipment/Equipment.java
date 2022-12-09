package ru.ivanmurzin.falloutdungeon.lib.item.equipment;

import org.jetbrains.annotations.NotNull;

import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Special;

public abstract class Equipment extends Item {
    @NotNull
    public final Special enchantScale = new Special();

    public Equipment(String name) {
        super(name);
    }
}
