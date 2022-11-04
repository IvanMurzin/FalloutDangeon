package ru.ivanmurzin.falloutdungeon.lib.item.aid;

public class CommonAid extends Aid {
    public final int health;

    public CommonAid(String name, int weight, int health) {
        super(name, weight);
        this.health = health;
    }
}
