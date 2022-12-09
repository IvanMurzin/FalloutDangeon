package ru.ivanmurzin.falloutdungeon.lib.unit.hero;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ConstantConditions")
public class Special {

    private final Map<SpecialType, SpecialItem> special = new HashMap<>();
    private int maxHealth;

    public Special() {
        for (SpecialType key : SpecialType.values()) {
            special.put(key, new SpecialItem(String.valueOf(key.name().charAt(0)), key.name(), 0));
        }
    }

    public static Special getSpecialWithScale(SpecialType type, int scale) {
        Special special = new Special();
        special.setSpecial(type, scale);
        return special;
    }

    public Map<SpecialType, SpecialItem> getSpecial() {
        return special;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public SpecialItem getSpecial(SpecialType type) {
        return special.get(type);
    }

    public void setSpecial(SpecialType type, int value) {
        if (value <= 0 || value > 10) return;
        special.get(type).setValue(value);
    }

    public double getMaxWeight() {
        return 2 + special.get(SpecialType.Strength).getValue() / 2d; // 2 + strength / 2
    }

    public int getHitChance() {
        int agility = special.get(SpecialType.Agility).getValue() * 5;
        int luck = special.get(SpecialType.Luck).getValue() * 2;
        return 30 + agility + luck; // 30% + agility * 5% + luck * 2%
    }

    public int getHitChance(Special special) {
        return getHitChance()
                + special.getSpecial(SpecialType.Agility).getValue() * 5
                + special.getSpecial(SpecialType.Luck).getValue() * 2;
    }

    public double getResistance() {
        return special.get(SpecialType.Endurance).getValue() / 2d * 0.05; // endurance / 2 * 0.05
    }

    public double getExperienceMultiplier() {
        double intelligence = special.get(SpecialType.Intelligence).getValue() / 20d;
        double luck = special.get(SpecialType.Agility).getValue() / 50d;
        return 1 + intelligence + luck; // 1 + intelligence / 2 + luck / 5
    }

    public int getHackChance(int difficult) {
        int perception = special.get(SpecialType.Perception).getValue() * 5;
        int luck = special.get(SpecialType.Agility).getValue() * 2;
        return 40 + perception + luck - 10 * difficult; // 40% + perception * 5% + luck * 2% - difficult * 10%
    }

    public int getHealthBonus() {
        return special.get(SpecialType.Endurance).getValue() * 10; // 10 * perception
    }

}
