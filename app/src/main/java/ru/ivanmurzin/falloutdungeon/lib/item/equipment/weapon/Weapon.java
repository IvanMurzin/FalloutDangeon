package ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon;

import static java.lang.Math.abs;

import androidx.annotation.Nullable;

import ru.ivanmurzin.falloutdungeon.controller.Logger;
import ru.ivanmurzin.falloutdungeon.lib.MovingGameObject;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.Equipment;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Special;

public abstract class Weapon extends Equipment {
    public final int reloadTime;
    protected final int maxDamage;
    protected final WeaponType type;
    protected final int criticalChance;

    public Weapon(String name, int maxDamage, int reloadTime, WeaponType type, int criticalChance) {
        super(name);
        this.maxDamage = maxDamage;
        this.reloadTime = reloadTime;
        this.type = type;
        this.criticalChance = criticalChance;
    }

    public Bullet shoot(float x, float y, float speedX, float speedY, boolean fromHero) {
        return new Bullet(x, y, speedX, speedY, fromHero);
    }

    public abstract double getDamage(@Nullable Special special);

    public WeaponType getType() {
        return type;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    @Override
    public boolean pick(Logger logger) {
        logger.notifyInfo("Подобрано: " + name);
        Hero.getInstance().switchWeapon(this);
        return true;
    }

    public int getReload() {
        return reloadTime;
    }

    public enum Direction {
        South,
        North,
        West,
        East,
    }

    public class Bullet extends MovingGameObject {
        public final Direction direction;
        public final boolean fromHero;
        public final double damage;

        public Bullet(float x, float y, float speedX, float speedY, boolean fromHero) {
            super(x, y, 50);
            this.fromHero = fromHero;
            this.damage = getDamage(fromHero ? Hero.getInstance().special : null);
            if (speedX > 0 && abs(speedX) > abs(speedY)) {
                direction = Direction.East;
                return;
            }
            if (speedX < 0 && abs(speedX) > abs(speedY)) {
                direction = Direction.West;
                return;
            }
            if (speedY > 0) {
                direction = Direction.South;
                return;
            }
            direction = Direction.North;
        }

        public WeaponType getType() {
            return type;
        }

        @Override
        public void onMove() {
            switch (direction) {
                case South:
                    y += speed;
                    break;
                case North:
                    y -= speed;
                    break;
                case West:
                    x -= speed;
                    break;
                case East:
                    x += speed;
                    break;
            }
        }
    }

}
