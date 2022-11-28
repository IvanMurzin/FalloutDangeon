package ru.ivanmurzin.falloutdungeon.lib.item.aid;

import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Special;

public class SpecialAid extends Aid {
    public final Special special;

    public SpecialAid(int weight, Special special) {
        super("Особый элексир", weight);
        this.special = special;
    }
}
