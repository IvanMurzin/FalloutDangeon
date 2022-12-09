package ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon;

import ru.ivanmurzin.falloutdungeon.lib.unit.hero.SpecialType;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;

public class WeaponGenerator {
    public static Weapon getSimpleWeapon() {
        return RandomGenerator.isSuccess(70) ? new Pistol() : new LaserPistol();
    }

    public static Weapon getMiddleWeapon() {
        Weapon weapon;
        if (RandomGenerator.isSuccess(90)) {
            weapon = new LaserPistol();
        } else {
            weapon = new Pistol();
        }
        if (RandomGenerator.isSuccess(50)) {
            weapon.enchantScale.setSpecial(SpecialType.Agility, 2);
            weapon.enchantScale.setSpecial(SpecialType.Luck, 2);
        }
        return weapon;
    }

    public static Weapon getTopWeapon() {
        return RandomGenerator.isSuccess(80) ? new Cryolator() : new LaserPistol();
    }
}
