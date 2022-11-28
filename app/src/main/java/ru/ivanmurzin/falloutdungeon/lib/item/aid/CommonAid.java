package ru.ivanmurzin.falloutdungeon.lib.item.aid;

public class CommonAid extends Aid {
    public final int health;

    public CommonAid(int weight, int health) {
        super("Обычный элексир", weight);
        this.health = health;
    }
}
