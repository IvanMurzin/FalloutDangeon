package ru.ivanmurzin.falloutdungeon.lib.item.equipment;

import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.item.aid.CommonAid;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.LeatherBreastplate;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.LeatherHelmet;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.MetalBreastplate;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.MetalHelmet;
import ru.ivanmurzin.falloutdungeon.lib.item.lockpick.Lockpick;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;

public class ItemGenerator {
    public static Item getSimpleItem() {
        if (RandomGenerator.isSuccess(30)) {
            return RandomGenerator.isSuccess(70) ? new Lockpick(3) : new Lockpick(5);
        }
        return new CommonAid(1, 15);
    }

    public static Item getMiddleItem() {
        if (RandomGenerator.isSuccess(70)) {
            return RandomGenerator.isSuccess(60) ? new LeatherHelmet() : new LeatherBreastplate();
        }
        return RandomGenerator.isSuccess(60) ? new MetalHelmet() : new MetalBreastplate();
    }
}
