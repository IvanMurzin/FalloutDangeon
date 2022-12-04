package ru.ivanmurzin.falloutdungeon.lib.unit.enemy;

import ru.ivanmurzin.falloutdungeon.lib.game.Level;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.LaserPistol;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;
import ru.ivanmurzin.falloutdungeon.lib.item.lockpick.Lockpick;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;

public class SuperMutant extends Enemy {

    public SuperMutant(float x, float y, Level level) {
        super(x, y, 4, 100, 15, new LaserPistol(), 400, 100, level);
        typeResistance.put(WeaponType.Laser, 0.7);
    }

    public Item getDrop() {
        if (RandomGenerator.isSuccess(80)) {
            return new LaserPistol();
        }
        return new Lockpick(5);
    }
}
