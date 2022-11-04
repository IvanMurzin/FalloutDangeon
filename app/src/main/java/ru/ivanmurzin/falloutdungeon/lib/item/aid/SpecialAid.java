package ru.ivanmurzin.falloutdungeon.lib.item.aid;

import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Special;

public class SpecialAid extends Aid {
    public final Special special;

    public SpecialAid(String name, int weight, Special special) {
        super(name, weight);
        this.special = special;
    }
}
