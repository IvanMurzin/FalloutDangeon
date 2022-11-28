package ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon;

import static java.lang.Math.abs;

import androidx.annotation.Nullable;

import java.util.Map;

import ru.ivanmurzin.falloutdungeon.lib.MovingGameObject;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.Equipment;
import ru.ivanmurzin.falloutdungeon.lib.unit.enemy.Enemy;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Special;

public abstract class Weapon extends Equipment {
    protected final int maxDamage;
    protected final WeaponType type;
    protected final int criticalChance;
    protected final Map<Class<Enemy>, Double> legendaryScale;


    public Weapon(String name, Special enchantScale, int maxDamage, WeaponType type, int criticalChance, Map<Class<Enemy>, Double> legendaryScale) {
        super(name, enchantScale);
        this.maxDamage = maxDamage;
        this.type = type;
        this.criticalChance = criticalChance;
        this.legendaryScale = legendaryScale;
    }

    public abstract double getDamage(@Nullable Special special);

    public abstract Bullet shoot(float x, float y, float speedX, float speedY, boolean fromHero);

    public WeaponType getType() {
        return type;
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
            super(x, y, 100, true);
            this.fromHero = fromHero;
            this.damage = getDamage(fromHero ? null : Hero.instance.special);
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
        public void move() {
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
