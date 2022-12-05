package ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor;

import android.util.Pair;

import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;

public class LeatherBreastplate extends Armor {
    public LeatherBreastplate() {
        super("Кожанный нагрудник", null, 2, ArmorType.Breastplate, new Pair<>(WeaponType.Laser, 1.5));
    }
}
