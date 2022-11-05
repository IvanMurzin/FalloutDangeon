package ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor;

import java.util.Map;

import ru.ivanmurzin.falloutdungeon.lib.item.equipment.Equipment;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Special;

public abstract class Armor extends Equipment {
    protected final double armor;
    protected final Map<WeaponType, Double> typeResistance;

    public Armor(String name, Special enchantScale, int armor, Map<WeaponType, Double> typeResistance) {
        super(name, enchantScale);
        this.armor = armor;
        this.typeResistance = typeResistance;
    }

    public double getArmor(Special special, WeaponType type) {
        double resistance = special.getResistance() + enchantScale.getResistance();
        Double typeRes = typeResistance.get(type);
        if (typeRes == null) typeRes = 0d;
        return armor + resistance + typeRes;
    }
}
