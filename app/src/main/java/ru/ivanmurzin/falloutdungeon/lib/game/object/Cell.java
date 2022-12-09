package ru.ivanmurzin.falloutdungeon.lib.game.object;

import ru.ivanmurzin.falloutdungeon.lib.GameObject;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;

public class Cell extends GameObject {
    public final int type;

    public Cell(int x, int y) {
        super(x, y);
        this.type = RandomGenerator.getRandom(0, 4);
    }
}
