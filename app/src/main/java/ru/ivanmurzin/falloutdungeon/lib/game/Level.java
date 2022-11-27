package ru.ivanmurzin.falloutdungeon.lib.game;

import android.content.Context;

import java.util.HashSet;
import java.util.Set;

import ru.ivanmurzin.falloutdungeon.lib.game.object.Cell;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.Chest;

public class Level {
    //    public final Enemy[] enemies;
    public final int levelNumber;
    public final int fieldSize;
    public final Cell[][] cells;
    public final Set<IntractableGameObject> objects;
    private Level nextLevel;
    private Level previousLevel;

    public Level(Context context, int levelNumber, int fieldSize) {
        cells = new Cell[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                cells[i][j] = new Cell(i * 40, j * 40);
            }
        }
//        this.enemies = new Enemy[10];
        this.levelNumber = levelNumber;
        this.fieldSize = fieldSize;
        this.objects = new HashSet<>();
    }

    public void addChests(Set<Chest> chests) {
        objects.addAll(chests);
    }

    public Level getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(Level nextLevel) {
        this.nextLevel = nextLevel;
    }

    public Level getPreviousLevel() {
        return previousLevel;
    }


}
