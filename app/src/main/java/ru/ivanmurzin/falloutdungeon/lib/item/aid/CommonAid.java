package ru.ivanmurzin.falloutdungeon.lib.item.aid;

import androidx.annotation.NonNull;

import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;

public class CommonAid extends Aid {
    public final int health;

    public CommonAid(int weight, int health) {
        super("Обычный элексир", weight);
        this.health = health;
    }

    @Override
    public void use() {
        Hero.getInstance().increaseHealth(health);
    }

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(health);
    }
}
