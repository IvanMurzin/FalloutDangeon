package ru.ivanmurzin.falloutdungeon.lib.unit;

import ru.ivanmurzin.falloutdungeon.lib.MovingGameObject;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;

/**
 * This class provides a skeletal implementation of the game's unit.
 *
 * <p> To create your own unit in the game, it is enough to inherit from this class and provide
 * implementation for {@link #takeDamage(double, WeaponType)}, {@link #onShoot(Weapon.Bullet)}
 * and {@link #onDie()} methods.
 */
abstract public class Unit extends MovingGameObject {
    protected double maxHealth;
    protected double health;
    protected int armor;

    /**
     * Creates a unit at specific coordinates with the specified speed, health, and armor
     *
     * @param x         unit's x coordinate
     * @param y         unit's y coordinate
     * @param speed     unit's speed in pixel by frame
     * @param maxHealth unit's max health
     * @param armor     default unit's armor
     */
    public Unit(float x, float y, float speed, int maxHealth, int armor) {
        super(x, y, speed);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.armor = armor;
    }

    /**
     * Creates a unit at (0,0) coordinates with the 30 speed and specific health, and armor
     *
     * @param maxHealth unit's max health
     * @param armor     default unit's armor
     */
    public Unit(int maxHealth, int armor) {
        this(0, 0, 30, maxHealth, armor);
    }

    /**
     * @return max health of the unit
     */
    public double getMaxHealth() {
        return maxHealth;
    }

    /**
     * @return current health of the unit
     */
    public double getHealth() {
        return health;
    }

    /**
     * Increase current health, but not above max health
     *
     * @param health unit health change
     */
    public void increaseHealth(double health) {
        this.health += health;
        if (this.health > maxHealth) this.health = maxHealth;
    }

    /**
     * A method that describes how and based on what characteristics the unit should receive damage
     *
     * @param damage the pure damage the unit should take
     * @param type   type of weapon from which this damage was dealt
     */
    public abstract void takeDamage(double damage, WeaponType type);

    /**
     * method that is called when a unit is hit by a bullet
     *
     * @param bullet bullet hitting a unit
     * @return true if the bullet has collided, false otherwise
     */
    public abstract boolean onShoot(Weapon.Bullet bullet);

    /**
     * method that is called when the character dies
     */
    public abstract void onDie();
}
