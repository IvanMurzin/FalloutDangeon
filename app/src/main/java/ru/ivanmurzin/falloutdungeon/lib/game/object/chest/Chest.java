package ru.ivanmurzin.falloutdungeon.lib.game.object.chest;

import android.util.Log;

import ru.ivanmurzin.falloutdungeon.lib.game.IntractableGameObject;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;

public class Chest extends IntractableGameObject {
    public final Item item;
    public final int difficulty;
    public final ChestType type;

    public Chest(int x, int y, Item item, int difficulty, ChestType type) {
        super(x, y);
        this.item = item;
        this.difficulty = difficulty;
        this.type = type;
    }

    @Override
    public void action() {
        Log.d("RRR", difficulty + "");
    }
}
