package ru.ivanmurzin.falloutdungeon.controller.object;


import static ru.ivanmurzin.falloutdungeon.controller.generator.LevelGenerator.TILE_SIZE;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.controller.adapter.GameObjectAdapter;
import ru.ivanmurzin.falloutdungeon.controller.generator.LevelGenerator;
import ru.ivanmurzin.falloutdungeon.controller.object.unit.HeroController;
import ru.ivanmurzin.falloutdungeon.controller.ui.JoystickController;
import ru.ivanmurzin.falloutdungeon.lib.game.Level;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class LevelController {
    private final GameObjectAdapter gameObjectAdapter;
    private final HeroController heroController;
    private final Bitmap defaultBitmap;
    private final Bitmap fence;
    LevelGenerator generator;
    private final Level level;

    public LevelController(Context context, int width, int height) {
        generator = new LevelGenerator(context);
        level = generator.getFirstLevel();
        heroController = new HeroController(context, level, width, height, TILE_SIZE);
        gameObjectAdapter = new GameObjectAdapter(context, level);

        Bitmap[] cellBitmaps = new Bitmap[4];
        cellBitmaps[0] = BitmapUtil.getScaledBitmap(context, TILE_SIZE, TILE_SIZE, R.drawable.tile1);
        cellBitmaps[1] = BitmapUtil.getScaledBitmap(context, TILE_SIZE, TILE_SIZE, R.drawable.tile2);
        cellBitmaps[2] = BitmapUtil.getScaledBitmap(context, TILE_SIZE, TILE_SIZE, R.drawable.tile3);
        cellBitmaps[3] = BitmapUtil.getScaledBitmap(context, TILE_SIZE, TILE_SIZE, R.drawable.tile4);
        defaultBitmap = BitmapUtil.getScaledBitmap(context, TILE_SIZE, TILE_SIZE, R.drawable.tile1);
        for (int i = 1; i < level.cells.length - 1; i++) {
            for (int j = 1; j < level.cells[i].length - 1; j++) {
                level.cells[i][j].setBitmap(cellBitmaps[RandomGenerator.getRandom(0, cellBitmaps.length)]);
            }
        }
        fence = BitmapUtil.getScaledBitmap(context, TILE_SIZE, 60, R.drawable.fence);
    }

    public void draw(Canvas canvas, GameDisplay display) {
        canvas.drawColor(Color.BLACK);
        for (int i = 1; i < level.cells.length - 1; i++) {
            for (int j = 1; j < level.cells[i].length - 1; j++) {
                Bitmap cellBitmap = level.cells[i][j].getBitmap();
                Bitmap bitmap = cellBitmap == null ? defaultBitmap : cellBitmap;
                canvas.drawBitmap(bitmap, display.offsetX(level.cells[i][j].x), display.offsetY(level.cells[i][j].y), null);
            }
        }
        for (int i = 0; i < level.cells.length; i++) {
            canvas.drawBitmap(fence, display.offsetX(0), display.offsetY(i * TILE_SIZE), null);
            canvas.drawBitmap(fence, display.offsetX((level.fieldSize - 1) * TILE_SIZE), display.offsetY(i * TILE_SIZE), null);
            canvas.drawBitmap(fence, display.offsetX(i * TILE_SIZE), display.offsetY(0), null);
            canvas.drawBitmap(fence, display.offsetX(i * TILE_SIZE), display.offsetY((level.fieldSize - 1) * TILE_SIZE), null);
        }

        gameObjectAdapter.draw(canvas, display);
        heroController.draw(canvas, display);
    }


    public void update() {
        heroController.update();
        level.update();
    }

    public JoystickController getJoystickController() {
        return heroController.joystickAdapter.controller;
    }

    public void onTouchEvent(MotionEvent event) {
        heroController.onTouchEvent(event);
    }
}
