package ru.ivanmurzin.falloutdungeon.lib.game.object.chest;

import ru.ivanmurzin.falloutdungeon.lib.GameObject;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;

public class Chest extends GameObject {
    public final Item item;
    public final int difficulty;

    public Chest(int x, int y, Item item, int difficulty) {
        super(x, y);
        this.item = item;
        this.difficulty = difficulty;
    }
}
