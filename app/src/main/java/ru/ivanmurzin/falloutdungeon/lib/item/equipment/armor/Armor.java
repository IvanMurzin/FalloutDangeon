package ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor;

import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;

import ru.ivanmurzin.falloutdungeon.Constants;
import ru.ivanmurzin.falloutdungeon.controller.Logger;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.Equipment;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;

public class Armor extends Equipment {
    public final int id;
    public final ArmorType type;
    protected final double defence;
    protected final Pair<WeaponType, Double> typeResistance;

    public Armor(int id, String name, double defence, ArmorType type, Pair<WeaponType, Double> typeResistance) {
        super(name);
        this.id = id;
        this.defence = defence;
        this.type = type;
        this.typeResistance = typeResistance;
    }

    public WeaponType getResistanceType() {
        return typeResistance.first;
    }

    public double getArmor(WeaponType type) {
        double enchantResistance = 1 + enchantScale.getResistance();
        double scale = type == typeResistance.first ? typeResistance.second : 1;
        Log.v(Constants.TAG_V + "_GET_ARMOR", "enchantResistance=" + enchantResistance + "\nscale=" + scale + "\narmor=" + defence * scale * enchantResistance);
        return defence * scale * enchantResistance;
    }

    public double getDefence() {
        return defence;
    }

    @Override
    public boolean pick(Logger logger) {
        logger.notifyInfo("Надето: " + name);
        Hero.instance.switchArmor(this);
        return true;
    }

    @NonNull
    @Override
    public String toString() {
        return "Armor{" +
                "name=" + name +
                ", type=" + type +
                ", defence=" + defence +
                ", typeResistance=" + typeResistance +
                '}';
    }
}
