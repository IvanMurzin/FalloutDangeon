package ru.ivanmurzin.falloutdungeon.controller.generator;

import android.content.Context;

import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.item.aid.CommonAid;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.Armor;
import ru.ivanmurzin.falloutdungeon.lib.item.lockpick.Lockpick;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.SpecialType;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;
import ru.ivanmurzin.falloutdungeon.util.YamlLoader;

public class ItemGenerator {
    private final Armor[] armors;

    public ItemGenerator(Context context) {
        YamlLoader loader = new YamlLoader(context);
        armors = new Armor[loader.armorList.size()];
        loader.armorList.toArray(armors);
    }

    public Item getSimpleItem() {
        if (RandomGenerator.isSuccess(30)) {
            return RandomGenerator.isSuccess(70) ? new Lockpick(3) : new Lockpick(5);
        }
        return new CommonAid(1, 15);
    }

    public Armor getArmor() {
        Armor armor = armors[RandomGenerator.getRandom(0, armors.length)];
        if (RandomGenerator.isSuccess(50)) {
            armor.enchantScale.setSpecial(SpecialType.Endurance, 3);
        }
        return armor;
    }
}
