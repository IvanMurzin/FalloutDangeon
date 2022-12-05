package ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor;

import android.util.Pair;

import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;

public class MetalBreastplate extends Armor {
    public MetalBreastplate() {
        super("металлический нагрудник", null, 4, ArmorType.Breastplate, new Pair<>(WeaponType.Ordinary, 1.6));
    }
}
