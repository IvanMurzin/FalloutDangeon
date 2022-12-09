package ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor;

import androidx.annotation.Nullable;

public enum ArmorType {
    Helmet,
    Breastplate;

    @Nullable
    public static ArmorType getArmorTypeFromString(String string) {
        for (ArmorType type : values()) {
            if (string.equals(type.name())) return type;
        }
        return null;
    }
}

