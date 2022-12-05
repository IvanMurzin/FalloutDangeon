package ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor;

import android.util.Pair;

import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;

public class LeatherHelmet extends Armor {
    public LeatherHelmet() {
        super("Кожанный шлем", null, 1, ArmorType.Helmet, new Pair<>(WeaponType.Laser, 1.2));
    }
}
