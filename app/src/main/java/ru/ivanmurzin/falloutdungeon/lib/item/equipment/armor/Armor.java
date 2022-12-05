package ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor;

import android.util.Pair;

import ru.ivanmurzin.falloutdungeon.controller.Logger;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.Equipment;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Special;

public abstract class Armor extends Equipment {
    public final ArmorType type;
    protected final double defaultArmor;
    protected final Pair<WeaponType, Double> typeResistance;

    public Armor(String name, Special enchantScale, int defaultArmor, ArmorType type, Pair<WeaponType, Double> typeResistance) {
        super(name, enchantScale);
        this.defaultArmor = defaultArmor;
        this.type = type;
        this.typeResistance = typeResistance;
    }

    public WeaponType getResistanceType() {
        return typeResistance.first;
    }

    public double getArmor(WeaponType type) {
        double enchantResistance = enchantScale == null ? 1 : enchantScale.getResistance();
        double scale = type == typeResistance.first ? typeResistance.second : 1;
        return defaultArmor * scale * enchantResistance;
    }

    public double getDefaultArmor() {
        return defaultArmor;
    }

    @Override
    public void pick(Logger logger) {
        logger.notifyInfo("Надето: " + name);
        Hero.instance.switchArmor(this);
    }
}
