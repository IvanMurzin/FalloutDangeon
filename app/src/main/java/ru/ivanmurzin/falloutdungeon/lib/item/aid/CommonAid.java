package ru.ivanmurzin.falloutdungeon.lib.item.aid;

import ru.ivanmurzin.falloutdungeon.controller.Logger;

public class CommonAid extends Aid {
    public final int health;

    public CommonAid(int weight, int health) {
        super("Обычный элексир", weight);
        this.health = health;
    }

    @Override
    public void pick(Logger logger) {

    }
}
