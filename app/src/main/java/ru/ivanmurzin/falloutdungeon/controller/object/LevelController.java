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
import ru.ivanmurzin.falloutdungeon.lib.game.Field;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class LevelController {
    private final GameObjectAdapter gameObjectAdapter;
    private final HeroController heroController;
    private final Bitmap fence;
    private final Bitmap[] cellBitmaps = new Bitmap[4];
    private final Field field;

    public LevelController(Context context, int width, int height) {
        field = new Field();
        LevelGenerator.generateLevels(context, field);
        heroController = new HeroController(context, field, width, height);
        gameObjectAdapter = new GameObjectAdapter(context, field);

        cellBitmaps[0] = BitmapUtil.getScaledBitmap(context, TILE_SIZE, TILE_SIZE, R.drawable.tile1);
        cellBitmaps[1] = BitmapUtil.getScaledBitmap(context, TILE_SIZE, TILE_SIZE, R.drawable.tile2);
        cellBitmaps[2] = BitmapUtil.getScaledBitmap(context, TILE_SIZE, TILE_SIZE, R.drawable.tile3);
        cellBitmaps[3] = BitmapUtil.getScaledBitmap(context, TILE_SIZE, TILE_SIZE, R.drawable.tile4);
        fence = BitmapUtil.getScaledBitmap(context, TILE_SIZE, 60, R.drawable.fence);
    }

    public void draw(Canvas canvas, GameDisplay display) {
        canvas.drawColor(Color.BLACK);
        for (int i = 1; i < field.getLevel().cells.length - 1; i++) {
            for (int j = 1; j < field.getLevel().cells[i].length - 1; j++) {
                int type = field.getLevel().cells[i][j].type;
                canvas.drawBitmap(cellBitmaps[type], display.offsetX(field.getLevel().cells[i][j].x), display.offsetY(field.getLevel().cells[i][j].y), null);
            }
        }
        for (int i = 0; i < field.getLevel().cells.length; i++) {
            canvas.drawBitmap(fence, display.offsetX(0), display.offsetY(i * TILE_SIZE), null);
            canvas.drawBitmap(fence, display.offsetX((field.getLevel().fieldSize - 1) * TILE_SIZE), display.offsetY(i * TILE_SIZE), null);
            canvas.drawBitmap(fence, display.offsetX(i * TILE_SIZE), display.offsetY(0), null);
            canvas.drawBitmap(fence, display.offsetX(i * TILE_SIZE), display.offsetY((field.getLevel().fieldSize - 1) * TILE_SIZE), null);
        }

        gameObjectAdapter.draw(canvas, display);
        heroController.draw(canvas, display);
    }


    public void update() {
        heroController.update();
        field.getLevel().update();
    }

    public JoystickController getJoystickController() {
        return heroController.joystickAdapter.controller;
    }

    public void onTouchEvent(MotionEvent event) {
        heroController.onTouchEvent(event);
    }
}
