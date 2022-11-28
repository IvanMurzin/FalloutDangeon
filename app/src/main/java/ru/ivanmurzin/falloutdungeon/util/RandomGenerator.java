package ru.ivanmurzin.falloutdungeon.util;

import android.util.Log;

import java.util.Random;

import ru.ivanmurzin.falloutdungeon.Constants;

public class RandomGenerator {
    private static final Random random = new Random();

    public static int getRandom(int min, int max) {
        return min + (int) (Math.random() * (max - min));
    }

    public static boolean isSuccess(int chance) {
        int randomInt = random.nextInt(100);
        Log.i(Constants.TAG, "isSuccess: chance=" + chance + " random=" + randomInt);
        return chance >= randomInt;
    }

    public static int getFromMax(int max) {
        return (int) (max * getRandom(50, 100) / 100f);
    }

}
