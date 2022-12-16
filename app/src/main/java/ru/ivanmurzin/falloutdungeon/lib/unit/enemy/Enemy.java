package ru.ivanmurzin.falloutdungeon.lib.unit.enemy;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import ru.ivanmurzin.falloutdungeon.Constants;
import ru.ivanmurzin.falloutdungeon.lib.game.Field;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;
import ru.ivanmurzin.falloutdungeon.lib.unit.Unit;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;

/**
 * This class provides a skeletal implementation of the enemy.
 **/
public abstract class Enemy extends Unit {
    public final Weapon weapon;
    public final int dropExperience;
    protected final Map<WeaponType, Double> typeResistance;
    protected final int viewRadius;
    private final Field.Level level;
    protected int reload = 0;

    /**
     * Creates a unit at specific coordinates with the specified speed, max health, armor, weapon, view radius, drop experience and level
     *
     * @param x              unit's x coordinate
     * @param y              unit's y coordinate
     * @param speed          unit's speed in pixels per frame
     * @param maxHealth      unit's max health
     * @param armor          default unit's armor
     * @param weapon         unit's weapon
     * @param viewRadius     unit's view radius
     * @param dropExperience experience that will be awarded to the hero after the death of this enemy
     * @param level          the level at which the hero is
     */
    public Enemy(float x, float y, float speed, int maxHealth, int armor, Weapon weapon, int viewRadius, int dropExperience, Field.Level level) {
        super(x, y, speed, maxHealth, armor);
        this.weapon = weapon;
        this.dropExperience = dropExperience;
        this.typeResistance = new HashMap<>();
        this.viewRadius = viewRadius;
        this.level = level;
    }

    /**
     * A method that describes the rules for dropping items from an enemy
     *
     * @return item that can be picked up
     */
    public abstract Item getDrop();

    /**
     * The enemy moves towards the hero as soon as he enters the view radius and stops
     * when he reaches a distance of 150 pixels
     */
    @Override
    public void onMove() {
        double distance = Hero.getInstance().getDistance(x + 50, y + 40);
        if (distance < 600) {
            float dx = Hero.getInstance().x - x;
            float dy = Hero.getInstance().y - y;
            if (reload == 0) {
                level.addBullet(weapon.shoot(x + 50, y + 40, dx, dy, false));
            }
            reload = (reload + 1) % weapon.reloadTime;
            if (distance < 150) return;
            if (dx > 0) x += speed;
            else x -= speed;
            if (dy > 0) y += speed;
            else y -= speed;
        }
    }

    /**
     * Calculates damage based on weapon type resistance. Health cannot drop below 0
     *
     * @param damage the pure damage the unit should take
     * @param type   type of weapon from which this damage was dealt
     */
    @Override
    public void takeDamage(double damage, WeaponType type) {
        if (typeResistance != null) {
            Double scale = typeResistance.getOrDefault(type, 1.);
            if (scale != null) {
                damage *= scale;
            }
        }
        health -= damage;
        if (health < 0) health = 0;
    }

    /**
     * Leaves an item on the level in its place
     */
    @Override
    public void onDie() {
        level.addDroppedItem(getDrop(), x, y);
        Hero.getInstance().experience.accrueExperience(dropExperience);
    }

    /**
     * If the bullet is not from the hero, the method {@link #takeDamage(double, WeaponType)} is called
     *
     * @param bullet bullet hitting a unit
     * @return true if the bullet has collided, false otherwise
     */
    @Override
    public boolean onShoot(Weapon.Bullet bullet) {
        if (!bullet.fromHero) return false;
        Log.i(Constants.TAG_I, "Hit:\t" + getClass().getSimpleName() + "\thp:" + this.health + "\tdmg:" + bullet.damage);
        takeDamage(bullet.damage, bullet.getType());
        return true;
    }
}
