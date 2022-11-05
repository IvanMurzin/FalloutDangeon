package ru.ivanmurzin.falloutdungeon.controller.object;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.lib.game.Level;
import ru.ivanmurzin.falloutdungeon.lib.game.object.Cell;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class LevelController extends DrawController {
    private final Level level;
    private final Bitmap[] cellBitmaps;
    private final Bitmap fence;

    public LevelController(Context context) {
        super(0, 0);
        level = new Level(context, 1, 40);
        cellBitmaps = new Bitmap[4];
        cellBitmaps[0] = BitmapUtil.getScaledBitmap(context, 40, 40, R.drawable.tile1);
        cellBitmaps[1] = BitmapUtil.getScaledBitmap(context, 40, 40, R.drawable.tile2);
        cellBitmaps[2] = BitmapUtil.getScaledBitmap(context, 40, 40, R.drawable.tile3);
        cellBitmaps[3] = BitmapUtil.getScaledBitmap(context, 40, 40, R.drawable.tile4);
        fence = BitmapUtil.getScaledBitmap(context, 40, 60, R.drawable.fence);
    }

    @Override
    public void draw(Canvas canvas, GameDisplay display) {
        canvas.drawColor(Color.BLACK);
        for (int i = 1; i < level.cells.length - 1; i++) {
            Cell[] cells = level.cells[i];
            for (int j = 1; j < cells.length - 1; j++) {
                canvas.drawBitmap(cellBitmaps[RandomGenerator.getRandom(0, cellBitmaps.length)], display.offsetX(cells[j].x), display.offsetY(cells[j].y), null);
            }
        }
        for (int i = 0; i < level.cells.length; i++) {
            canvas.drawBitmap(fence, display.offsetX(0), display.offsetY(i * 40), null);
            canvas.drawBitmap(fence, display.offsetX((level.fieldSize - 1) * 40), display.offsetY(i * 40), null);
            canvas.drawBitmap(fence, display.offsetX(i * 40), display.offsetY(0), null);
            canvas.drawBitmap(fence, display.offsetX(i * 40), display.offsetY((level.fieldSize - 1) * 40), null);
        }
    }
}
