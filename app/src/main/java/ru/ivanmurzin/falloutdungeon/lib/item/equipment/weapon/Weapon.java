package ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon;

import java.util.Map;

import ru.ivanmurzin.falloutdungeon.lib.item.equipment.Equipment;
import ru.ivanmurzin.falloutdungeon.lib.unit.enemy.Enemy;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Special;

public abstract class Weapon extends Equipment {
    protected final int maxDamage;
    protected final WeaponType type;
    protected final int criticalChance;
    protected final Map<Class<Enemy>, Double> legendaryScale;


    public Weapon(Special enchantScale, int maxDamage, WeaponType type, int criticalChance, Map<Class<Enemy>, Double> legendaryScale) {
        super(enchantScale);
        this.maxDamage = maxDamage;
        this.type = type;
        this.criticalChance = criticalChance;
        this.legendaryScale = legendaryScale;
    }

    public abstract int getDamage(Special special);

    public WeaponType getType() {
        return type;
    }
}
