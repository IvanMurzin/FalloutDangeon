package ru.ivanmurzin.falloutdungeon.controller;

import java.util.Random;

public class RandomController {
    public static Random random = new Random();

    public static boolean isSuccess(int chance) {
        return random.nextInt(100) < chance;
    }
}
