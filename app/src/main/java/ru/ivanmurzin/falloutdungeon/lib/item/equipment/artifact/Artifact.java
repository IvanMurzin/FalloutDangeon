package ru.ivanmurzin.falloutdungeon.lib.item.equipment.artifact;

import ru.ivanmurzin.falloutdungeon.controller.Logger;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.Equipment;

public class Artifact extends Equipment {

    public Artifact(String name) {
        super(name);
    }

    @Override
    public boolean pick(Logger logger) {
        return false;
    }
}
