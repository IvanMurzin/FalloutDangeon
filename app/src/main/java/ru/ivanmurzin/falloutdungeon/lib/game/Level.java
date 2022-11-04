package ru.ivanmurzin.falloutdungeon.lib.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.lib.game.object.Cell;
import ru.ivanmurzin.falloutdungeon.lib.game.object.Fence;
import ru.ivanmurzin.falloutdungeon.lib.unit.enemy.Enemy;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class Level {
    public final Hero hero;
    public final Enemy[] enemies;
    public final int levelNumber;
    public final int fieldSize;
    public final Cell[][] cells;

    public Level getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(Level nextLevel) {
        this.nextLevel = nextLevel;
    }

    public Level getPreviousLevel() {
        return previousLevel;
    }


    private Level nextLevel;
    private Level previousLevel;

    private int getRandom(int min, int max) {
        return min + (int) (Math.random() * (max - min));
    }

    public Level(Context context, Hero hero, int levelNumber, int fieldSize) {
        cells = new Cell[fieldSize][fieldSize];
        Bitmap[] bitmaps = new Bitmap[4];
        bitmaps[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.tile1), 40, 40, false);
        bitmaps[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.tile2), 40, 40, false);
        bitmaps[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.tile3), 40, 40, false);
        bitmaps[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.tile4), 40, 40, false);
        for (int i = 1; i < fieldSize - 1; i++) {
            for (int j = 1; j < fieldSize; j++) {
                cells[i][j] = new Cell(i * 40, j * 40, bitmaps[getRandom(0, 4)]);
            }
        }
        for (int i = 0; i < fieldSize; i++) {
            cells[0][i] = new Fence(context, 0, i * 40);
            cells[fieldSize - 1][i] = new Fence(context, (fieldSize - 1) * 40, i * 40);
            cells[i][0] = new Fence(context, i * 40, 0);
            cells[i][fieldSize - 1] = new Fence(context, i * 40, (fieldSize - 1) * 40);
        }
        this.hero = hero;
        this.enemies = new Enemy[10];
        this.levelNumber = levelNumber;
        this.fieldSize = fieldSize;
    }

    public void draw(Canvas canvas, GameDisplay display) {
        canvas.drawColor(Color.BLACK);
        for (Cell[] xCells : cells) {
            for (Cell cell : xCells) {
                cell.draw(canvas, display);
            }
        }
    }
}
