package ru.ivanmurzin.falloutdungeon.lib.unit.enemy;

import java.util.Map;

import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;

public class SuperMutant extends Enemy {
    public SuperMutant(int maxHealth, int armor, Weapon weapon, int dropExperience, Item drop, Map<WeaponType, Double> typeResistance) {
        super(maxHealth, armor, weapon, dropExperience, drop, typeResistance);
    }
}
