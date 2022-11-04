package ru.ivanmurzin.falloutdungeon.lib.unit.enemy;

import java.util.Map;

import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;
import ru.ivanmurzin.falloutdungeon.lib.unit.Unit;

public abstract class Enemy extends Unit {
    public final Weapon weapon;
    public final int dropExperience;
    public final Item drop;
    protected final Map<WeaponType, Double> typeResistance;

    public Enemy(int maxHealth, int armor, Weapon weapon, int dropExperience, Item drop, Map<WeaponType, Double> typeResistance) {
        super(maxHealth, armor);
        this.weapon = weapon;
        this.dropExperience = dropExperience;
        this.drop = drop;
        this.typeResistance = typeResistance;
    }
}
