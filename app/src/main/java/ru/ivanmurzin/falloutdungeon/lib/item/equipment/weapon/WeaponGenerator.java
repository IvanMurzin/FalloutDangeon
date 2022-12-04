package ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon;

import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;

public class WeaponGenerator {
    public static Weapon getSimpleWeapon() {
        return RandomGenerator.isSuccess(70) ? new Pistol() : new LaserPistol();
    }

    public static Weapon getMiddleWeapon() {
        return RandomGenerator.isSuccess(90) ? new LaserPistol() : new Pistol();
    }

    public static Weapon getTopWeapon() {
        return RandomGenerator.isSuccess(80) ? new Cryolator() : new LaserPistol();
    }
}
