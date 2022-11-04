package ru.ivanmurzin.falloutdungeon.lib.game.object;

import android.graphics.Bitmap;

import ru.ivanmurzin.falloutdungeon.lib.item.Item;

public class Chest extends GameObject {
    public final Item item;
    public final int difficulty;

    public Chest(int x, int y, Bitmap bitmap, Item item, int difficulty) {
        super(x, y, bitmap);
        this.item = item;
        this.difficulty = difficulty;
    }
}
