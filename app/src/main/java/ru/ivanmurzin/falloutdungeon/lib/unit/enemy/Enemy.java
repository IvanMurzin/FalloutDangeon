package ru.ivanmurzin.falloutdungeon.lib.unit.enemy;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import ru.ivanmurzin.falloutdungeon.Constants;
import ru.ivanmurzin.falloutdungeon.lib.game.Level;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;
import ru.ivanmurzin.falloutdungeon.lib.unit.Unit;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;

public abstract class Enemy extends Unit {
    public final Weapon weapon;
    public final int dropExperience;
    protected final Map<WeaponType, Double> typeResistance;
    protected final int viewRadius;
    private final Level level;
    protected int reload = 0;


    public Enemy(float x, float y, float speed, int maxHealth, int armor, Weapon weapon, int viewRadius, int dropExperience, Level level) {
        super(x, y, speed, maxHealth, armor);
        this.weapon = weapon;
        this.dropExperience = dropExperience;
        this.typeResistance = new HashMap<>();
        this.viewRadius = viewRadius;
        this.level = level;
    }

    public abstract Item getDrop();

    @Override
    public void onMove() {
        double distance = Hero.instance.getDistance(x + 50, y + 40);
        if (distance < 600) {
            float dx = Hero.instance.x - x;
            float dy = Hero.instance.y - y;
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

    @Override
    public void onDie() {
        level.addDroppedItem(getDrop(), x, y);
        Hero.instance.experience.accrueExperience(dropExperience);
    }

    @Override
    public boolean onShoot(Weapon.Bullet bullet) {
        if (!bullet.fromHero) return false;
        Log.i(Constants.TAG_I, "Hit:\t" + getClass().getSimpleName() + "\thp:" + this.health + "\tdmg:" + bullet.damage + "\tfrom:" + (bullet.fromHero ? "Hero" : "Enemy"));
        takeDamage(bullet.damage, bullet.getType());
        return true;
    }
}
