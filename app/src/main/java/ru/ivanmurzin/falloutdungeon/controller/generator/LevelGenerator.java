package ru.ivanmurzin.falloutdungeon.controller.generator;


import android.content.Context;

import java.util.LinkedList;
import java.util.List;

import ru.ivanmurzin.falloutdungeon.lib.game.Field;
import ru.ivanmurzin.falloutdungeon.lib.game.object.Door;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.Chest;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.ChestType;
import ru.ivanmurzin.falloutdungeon.lib.unit.Unit;
import ru.ivanmurzin.falloutdungeon.lib.unit.enemy.Raider;
import ru.ivanmurzin.falloutdungeon.lib.unit.enemy.SuperMutant;

public class LevelGenerator {
    public final static int TILE_SIZE = 40;

    public static void generateLevels(Context context, Field field) {
        ItemGenerator generator = new ItemGenerator(context);
        Field.Level first = getFirstLevel(generator, field);
        Field.Level second = getSecondLevel(generator, field);
        Field.Level third = getThirdLevel(generator, field);
        first.setNext(second);
        second.setNext(third);
        field.setLevel(first);
    }

    public static Field.Level getFirstLevel(ItemGenerator generator, Field field) {
        int fieldSize = 40;
        Door door = new Door(field, fieldSize * TILE_SIZE - 50, fieldSize * TILE_SIZE - 200);
        Field.Level level = new Field.Level(1, 40, door);

        List<Chest> chests = new LinkedList<>();
        chests.add(new Chest(200, 200, WeaponGenerator.getSimpleWeapon(), 1, ChestType.Weapon));
        chests.add(new Chest(400, 200, WeaponGenerator.getMiddleWeapon(), 2, ChestType.Weapon));
        chests.add(new Chest(200, 400, WeaponGenerator.getMiddleWeapon(), 3, ChestType.Weapon));
        chests.add(new Chest(400, 400, WeaponGenerator.getTopWeapon(), 4, ChestType.Weapon));

        chests.add(new Chest(1000, 200, generator.getArmor(), 1, ChestType.Rare));
        chests.add(new Chest(1200, 200, generator.getArmor(), 2, ChestType.Rare));
        chests.add(new Chest(1000, 400, generator.getArmor(), 3, ChestType.Rare));
        chests.add(new Chest(1200, 400, generator.getArmor(), 4, ChestType.Rare));

        chests.add(new Chest(200, 800, generator.getSimpleItem(), 1, ChestType.Ordinary));
        chests.add(new Chest(400, 800, generator.getSimpleItem(), 2, ChestType.Ordinary));
        chests.add(new Chest(200, 1000, generator.getSimpleItem(), 3, ChestType.Ordinary));
        chests.add(new Chest(400, 1000, generator.getSimpleItem(), 4, ChestType.Ordinary));
        chests.forEach(level::addChest);


        List<Unit> units = new LinkedList<>();
        units.add(new Raider(level.fieldSize * TILE_SIZE - 100, level.fieldSize * TILE_SIZE - 100, level));
        units.add(new Raider(50, 50, level));
        units.add(new Raider(850, 850, level));
        units.add(new SuperMutant(550, 550, level));
        units.forEach(level::addUnit);

        return level;
    }

    public static Field.Level getSecondLevel(ItemGenerator generator, Field field) {
        int fieldSize = 60;
        Door door = new Door(field, fieldSize * 40 - 50, fieldSize * 40 - 200);
        Field.Level level = new Field.Level(2, fieldSize, door);

        List<Chest> chests = new LinkedList<>();
        chests.add(new Chest(400, 400, WeaponGenerator.getTopWeapon(), 4, ChestType.Weapon));
        chests.add(new Chest(1000, 400, generator.getArmor(), 3, ChestType.Rare));
        chests.add(new Chest(200, 1000, generator.getSimpleItem(), 3, ChestType.Ordinary));
        chests.add(new Chest(400, 1000, generator.getSimpleItem(), 4, ChestType.Ordinary));
        chests.forEach(level::addChest);


        List<Unit> units = new LinkedList<>();
        units.add(new SuperMutant(550, 550, level));
        units.add(new SuperMutant(200, 250, level));
        units.add(new SuperMutant(1000, 1550, level));
        units.add(new SuperMutant(1200, 550, level));
        units.add(new SuperMutant(1400, 50, level));
        units.add(new SuperMutant(1600, 350, level));
        units.forEach(level::addUnit);

        return level;
    }

    public static Field.Level getThirdLevel(ItemGenerator generator, Field field) {
        int fieldSize = 30;
        Door door = new Door(field, fieldSize * 40 - 50, fieldSize * 40 - 200);
        Field.Level level = new Field.Level(3, fieldSize, door);

        List<Chest> chests = new LinkedList<>();
        chests.add(new Chest(400, 400, WeaponGenerator.getTopWeapon(), 4, ChestType.Weapon));
        chests.add(new Chest(800, 700, generator.getArmor(), 3, ChestType.Rare));
        chests.add(new Chest(50, 200, generator.getSimpleItem(), 3, ChestType.Ordinary));
        chests.add(new Chest(300, 200, generator.getSimpleItem(), 4, ChestType.Ordinary));
        chests.forEach(level::addChest);

        List<Unit> units = new LinkedList<>();
        units.add(new SuperMutant(550, 550, level));
        units.add(new SuperMutant(200, 250, level));
        units.add(new SuperMutant(1000, 550, level));
        units.forEach(level::addUnit);

        return level;
    }
}
