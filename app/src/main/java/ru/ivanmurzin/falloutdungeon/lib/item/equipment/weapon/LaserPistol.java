package ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon;

import androidx.annotation.Nullable;

import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Special;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.SpecialType;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;


public class LaserPistol extends Weapon {
    public LaserPistol() {
        super("Лазерный пистолет", null, 15, 10, WeaponType.Laser, 15, null);
    }

    @Override
    public double getDamage(@Nullable Special special) {
        int specialHitChance = special == null ? 70 : special.getHitChance();
        int specialCriticalChance = special == null ? 15 : special.getSpecial(SpecialType.Luck).getValue();
        int enchantHitChance = enchantScale == null ? 0 : enchantScale.getHitChance();
        int enchantCriticalChance = enchantScale == null ? 0 : enchantScale.getSpecial(SpecialType.Luck).getValue();
        int hitChance = specialHitChance + enchantHitChance;
        if (!RandomGenerator.isSuccess(hitChance)) return 0;
        int resultCriticalChance = criticalChance + specialCriticalChance + enchantCriticalChance;
        int criticalDamage = 0;
        int specialDamage = special == null ? 0 : special.getSpecial(SpecialType.Perception).getValue();
        if (RandomGenerator.isSuccess(resultCriticalChance)) criticalDamage = maxDamage;
        return RandomGenerator.getFromMax(maxDamage) + criticalDamage + specialDamage;
    }
}
