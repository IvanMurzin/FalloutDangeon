package ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon;

import androidx.annotation.Nullable;

public enum WeaponType {
    Ordinary,
    Frost,
    Fire,
    Rad,
    Laser;

    @Nullable
    public static WeaponType getWeaponTypeFromString(String string) {
        for (WeaponType type : values()) {
            if (string.equals(type.name())) return type;
        }
        return null;
    }
}
