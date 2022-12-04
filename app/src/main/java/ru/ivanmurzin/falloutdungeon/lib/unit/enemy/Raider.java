package ru.ivanmurzin.falloutdungeon.lib.unit.enemy;

import android.util.Log;

import ru.ivanmurzin.falloutdungeon.Constants;
import ru.ivanmurzin.falloutdungeon.lib.game.Level;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Pistol;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;
import ru.ivanmurzin.falloutdungeon.lib.item.lockpick.Lockpick;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;

public class Raider extends Enemy {
    private final Level level;
    private int reload = 0;

    public Raider(float x, float y, Level level) {
        super(x, y, 7, 100, 10, new Pistol(), 100, new Lockpick(3), null);
        this.level = level;
    }

    @Override
    public void onMove() {
        double distance = Hero.instance.getDistance(x + 50, y + 40);
        if (distance < 600) {
            float dx = Hero.instance.x - x;
            float dy = Hero.instance.y - y;
            if (reload == 0) {
                level.addBullet(weapon.shoot(x + 50, y + 40, dx, dy, false));
            }
            reload = (reload + 1) % 30;
            if (distance < 150) return;
            if (dx > 0) x += speed;
            else x -= speed;
            if (dy > 0) y += speed;
            else y -= speed;
        }
    }

    @Override
    public void takeDamage(double damage, WeaponType type) {
        health -= damage;
        if (health < 0) health = 0;
    }

    @Override
    public boolean onShoot(Weapon.Bullet bullet) {
        Log.d(Constants.TAG_D, "Hit: " + this.health + " " + bullet.damage + " from " + bullet.fromHero);
        if (!bullet.fromHero) return false;
        takeDamage(bullet.damage, bullet.getType());
        return true;
    }

    @Override
    public void onDie() {
        level.addDroppedItem(drop, x, y);
        Hero.instance.experience.accrueExperience(dropExperience);
    }
}
