package ru.ivanmurzin.falloutdungeon.lib.item.equipment;

import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.item.lockpick.Lockpick;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;

public class ItemGenerator {
    public static Item getSimpleItem() {
        return RandomGenerator.isSuccess(70) ? new Lockpick(3) : new Lockpick(5);
    }
}
