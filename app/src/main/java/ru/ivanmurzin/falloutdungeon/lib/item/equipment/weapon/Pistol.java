package ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon;

import androidx.annotation.Nullable;

import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Special;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.SpecialType;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;

public class Pistol extends Weapon {
    public Pistol() {
        super("10-мм пистолет", null, 10, 30, WeaponType.Ordinary, 10, null);
    }

    @Override
    public double getDamage(@Nullable Special special) {
        int specialHitChance = special == null ? 80 : special.getHitChance();
        int specialCriticalChance = special == null ? 10 : special.getSpecial(SpecialType.Luck).getValue();
        int enchantHitChance = enchantScale == null ? 0 : enchantScale.getHitChance();
        int enchantCriticalChance = enchantScale == null ? 0 : enchantScale.getSpecial(SpecialType.Luck).getValue();
        int hitChance = specialHitChance + enchantHitChance;
        if (!RandomGenerator.isSuccess(hitChance)) return 0;
        int resultCriticalChance = criticalChance + specialCriticalChance + enchantCriticalChance;
        int criticalDamage = 0;
        if (RandomGenerator.isSuccess(resultCriticalChance)) criticalDamage = maxDamage;
        return RandomGenerator.getFromMax(maxDamage) + criticalDamage;
    }

    @Override
    public Bullet shoot(float x, float y, float speedX, float speedY, boolean fromHero) {
        return new Bullet(x, y, speedX, speedY, fromHero);
    }
}
