package ru.ivanmurzin.falloutdungeon.lib.unit;

abstract public class Unit {
    protected int maxHealth;
    protected int health;
    protected int armor;

    public Unit(int maxHealth, int armor) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.armor = armor;
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
