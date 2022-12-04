package ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon;

import androidx.annotation.Nullable;

import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Special;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.SpecialType;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;


public class Cryolator extends Weapon {
    public Cryolator() {
        super("Криолятор", null, 10, 4, WeaponType.Frost, 5, null);
    }

    @Override
    public double getDamage(@Nullable Special special) {
        int specialHitChance = special == null ? 60 : special.getHitChance();
        int specialCriticalChance = special == null ? 15 : special.getSpecial(SpecialType.Luck).getValue();
        int enchantHitChance = enchantScale == null ? 0 : enchantScale.getHitChance();
        int enchantCriticalChance = enchantScale == null ? 0 : enchantScale.getSpecial(SpecialType.Luck).getValue();
        int hitChance = specialHitChance + enchantHitChance;
        if (!RandomGenerator.isSuccess(hitChance)) return 0;
        int resultCriticalChance = criticalChance + specialCriticalChance + enchantCriticalChance;
        int criticalDamage = 0;
        int specialDamage = special == null ? 0 : special.getSpecial(SpecialType.Strength).getValue();
        if (RandomGenerator.isSuccess(resultCriticalChance)) criticalDamage = maxDamage;
        return RandomGenerator.getFromMax(maxDamage) + criticalDamage + specialDamage;
    }
}
