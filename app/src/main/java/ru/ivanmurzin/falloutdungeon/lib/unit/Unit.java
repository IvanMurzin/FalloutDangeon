package ru.ivanmurzin.falloutdungeon.lib.unit;

import ru.ivanmurzin.falloutdungeon.lib.MovingGameObject;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;

abstract public class Unit extends MovingGameObject {
    protected double maxHealth;
    protected double health;
    protected int armor;

    public Unit(float x, float y, float speed, int maxHealth, int armor) {
        super(x, y, speed, false);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.armor = armor;
    }

    public Unit(int maxHealth, int armor) {
        this(0, 0, 0, maxHealth, armor);
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getHealth() {
        return health;
    }

    public int getArmor() {
        return armor;
    }

    public abstract void takeDamage(double damage, WeaponType type);

    public abstract void getShoot(Weapon.Bullet bullet);
}
