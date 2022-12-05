package ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor;

import android.util.Pair;

import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;

public class MetalHelmet extends Armor {
    public MetalHelmet() {
        super("Металлический шлем", null, 2, ArmorType.Helmet, new Pair<>(WeaponType.Ordinary, 1.3));
    }
}
