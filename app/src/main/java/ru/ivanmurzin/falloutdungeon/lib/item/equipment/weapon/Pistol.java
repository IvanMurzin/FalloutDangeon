package ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon;

import android.util.Log;

import androidx.annotation.Nullable;

import ru.ivanmurzin.falloutdungeon.Constants;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Special;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.SpecialType;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;

public class Pistol extends Weapon {
    public Pistol() {
        super("10-мм пистолет", 10, 30, WeaponType.Ordinary, 10);
    }

    @Override
    public double getDamage(@Nullable Special special) {
        int hitChance = special == null ? 70 : special.getHitChance(enchantScale);
        int specialCriticalChance = special == null ? 15 : special.getSpecial(SpecialType.Luck).getValue();
        int enchantCriticalChance = enchantScale.getSpecial(SpecialType.Luck).getValue();
        if (!RandomGenerator.isSuccess(hitChance)) return 0;
        int resultCriticalChance = criticalChance + specialCriticalChance + enchantCriticalChance;
        int criticalDamage = 0;
        if (RandomGenerator.isSuccess(resultCriticalChance)) criticalDamage = maxDamage;
        if (special != null)
            Log.v(Constants.TAG_V + "_GET_DAMAGE", "specialHitChance=" + special.getHitChance() + "\nhitChance=" + hitChance + "\nspecialCriticalChance=" + specialCriticalChance + "\nenchantCriticalChance=" + enchantCriticalChance + "\nresultCriticalChance=" + resultCriticalChance);
        return RandomGenerator.getFromMax(maxDamage) + criticalDamage;
    }

    @Override
    public Bullet shoot(float x, float y, float speedX, float speedY, boolean fromHero) {
        return new Bullet(x, y, speedX, speedY, fromHero);
    }
}
