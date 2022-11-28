package ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon;

import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Special;

public class LaserPistol extends Weapon {
    public LaserPistol() {
        super("Лазерный пистолет", null, 120, WeaponType.Laser, 15, null);
    }

    @Override
    public double getDamage(Special special) {
        return 0;
    }

    @Override
    public Bullet shoot(float x, float y, float speedX, float speedY, boolean fromHero) {
        return null;
    }
}
