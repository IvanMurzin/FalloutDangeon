package ru.ivanmurzin.falloutdungeon.lib.item.equipment.artifact;

import ru.ivanmurzin.falloutdungeon.controller.Logger;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.Equipment;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Special;

public class Artifact extends Equipment {

    public Artifact(String name, Special enchantScale) {
        super(name, enchantScale);
    }

    @Override
    public boolean pick(Logger logger) {
        return false;
    }
}
