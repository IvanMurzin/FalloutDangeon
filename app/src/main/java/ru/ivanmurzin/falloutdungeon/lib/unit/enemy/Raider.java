package ru.ivanmurzin.falloutdungeon.lib.unit.enemy;


import ru.ivanmurzin.falloutdungeon.lib.game.Field;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Pistol;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;
import ru.ivanmurzin.falloutdungeon.lib.item.lockpick.Lockpick;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;

public class Raider extends Enemy {

    public Raider(float x, float y, Field.Level level) {
        super(x, y, 7, 60, 10, new Pistol(), 600, 50, level);
        typeResistance.put(WeaponType.Ordinary, 0.8);
    }

    public Item getDrop() {
        if (RandomGenerator.isSuccess(70)) {
            return new Lockpick(3);
        }
        return new Pistol();
    }
}
