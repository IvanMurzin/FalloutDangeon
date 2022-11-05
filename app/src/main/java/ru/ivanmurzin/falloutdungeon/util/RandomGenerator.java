package ru.ivanmurzin.falloutdungeon.util;

import java.util.Random;

public class RandomGenerator {
//
//    public static final Weapon[] weapons = {
//            new Weapon("10-мм Пистолет", new Special(), 50, WeaponType.Ordinary, 5, new HashMap<>()),
//            new Weapon("Огнемет", Special.getSpecialWithScale(SpecialType.Strength, 3), 40, WeaponType.Fire, 10, new HashMap<>()),
//            new Weapon("Криопушка", Special.getSpecialWithScale(SpecialType.Intelligence, 5), 60, WeaponType.Frost, 3, new HashMap<>()),
//            new Weapon("Радиевый карабин", Special.getSpecialWithScale(SpecialType.Endurance, 3), 50, WeaponType.Rad, 10, new HashMap<>()),
//    };


    private static final Random random = new Random();

    public static int getRandom(int min, int max) {
        return min + (int) (Math.random() * (max - min));
    }

    public static boolean doesProc(int chance) {
        return chance >= random.nextInt(100);
    }

    public static int getFromMax(int max) {
        return (int) (max * getRandom(50, 100) / 100f);
    }

}
