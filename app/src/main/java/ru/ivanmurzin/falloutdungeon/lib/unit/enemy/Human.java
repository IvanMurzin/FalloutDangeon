package ru.ivanmurzin.falloutdungeon.lib.unit.enemy;

import java.util.Map;

import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;

public class Human extends Enemy {
    public Human(int x, int y, int maxHealth, int armor, Weapon weapon, int dropExperience, Item drop, Map<WeaponType, Double> typeResistance) {
        super(x, y, maxHealth, armor, weapon, dropExperience, drop, typeResistance);
    }
}
