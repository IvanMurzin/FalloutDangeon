package ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon;

import java.util.Map;

import ru.ivanmurzin.falloutdungeon.lib.item.equipment.Equipment;
import ru.ivanmurzin.falloutdungeon.lib.unit.enemy.Enemy;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Special;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;

public class Weapon extends Equipment {
    protected final int maxDamage;
    protected final WeaponType type;
    protected final int criticalChance;
    protected final Map<Class<Enemy>, Double> legendaryScale;


    public Weapon(String name, Special enchantScale, int maxDamage, WeaponType type, int criticalChance, Map<Class<Enemy>, Double> legendaryScale) {
        super(name, enchantScale);
        this.maxDamage = maxDamage;
        this.type = type;
        this.criticalChance = criticalChance;
        this.legendaryScale = legendaryScale;
    }

    public int getDamage(Special special) {
        int hitChance = special.getHitChance() + enchantScale.getHitChance();
        if (!RandomGenerator.doesProc(hitChance)) return 0;
        return RandomGenerator.getFromMax(maxDamage);
    }

    public WeaponType getType() {
        return type;
    }
}
