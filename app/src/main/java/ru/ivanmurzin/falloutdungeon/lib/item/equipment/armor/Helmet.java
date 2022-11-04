package ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor;

import java.util.Map;

import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Special;

public class Helmet extends Armor {
    public Helmet(Special enchantScale, int armor, Map<WeaponType, Double> typeResistance) {
        super(enchantScale, armor, typeResistance);
    }

    @Override
    public double getArmor(Special special, WeaponType type) {
        return 3 + super.getArmor(special, type);
    }
}
