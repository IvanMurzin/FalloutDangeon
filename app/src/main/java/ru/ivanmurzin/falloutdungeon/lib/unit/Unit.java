package ru.ivanmurzin.falloutdungeon.lib.unit;

import ru.ivanmurzin.falloutdungeon.lib.GameObject;

abstract public class Unit extends GameObject {
    protected int maxHealth;
    protected int health;
    protected int armor;

    public Unit(int x, int y, int maxHealth, int armor) {
        super(x, y);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.armor = armor;
    }

    public Unit(int maxHealth, int armor) {
        this(0, 0, maxHealth, armor);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getArmor() {
        return armor;
    }
}
