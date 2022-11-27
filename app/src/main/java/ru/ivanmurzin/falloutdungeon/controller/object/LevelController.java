package ru.ivanmurzin.falloutdungeon.controller.object;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import java.util.HashSet;
import java.util.Set;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.controller.ActionController;
import ru.ivanmurzin.falloutdungeon.lib.GameObject;
import ru.ivanmurzin.falloutdungeon.lib.game.IntractableGameObject;
import ru.ivanmurzin.falloutdungeon.lib.game.Level;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.Chest;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.ChestType;
import ru.ivanmurzin.falloutdungeon.lib.item.lockpick.Lockpick;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class LevelController extends DrawController {
    private final ChestController chestController;
    private final ActionController actionController;
    private final Level level;
    private final Hero hero = Hero.instance;
    private final Bitmap defaultBitmap;
    private final Bitmap fence;

    public LevelController(Context context, ActionController actionController) {
        this.actionController = actionController;
        level = new Level(context, 1, 40);
        Set<Chest> chests = getRandomChests();
        level.addChests(chests);
        chestController = new ChestController(context, chests);
        defaultBitmap = BitmapUtil.getScaledBitmap(context, 40, 40, R.drawable.tile1);
        Bitmap[] cellBitmaps = new Bitmap[4];
        cellBitmaps[0] = BitmapUtil.getScaledBitmap(context, 40, 40, R.drawable.tile1);
        cellBitmaps[1] = BitmapUtil.getScaledBitmap(context, 40, 40, R.drawable.tile2);
        cellBitmaps[2] = BitmapUtil.getScaledBitmap(context, 40, 40, R.drawable.tile3);
        cellBitmaps[3] = BitmapUtil.getScaledBitmap(context, 40, 40, R.drawable.tile4);
        for (int i = 1; i < level.cells.length - 1; i++) {
            for (int j = 1; j < level.cells[i].length - 1; j++) {
                level.cells[i][j].setBitmap(cellBitmaps[RandomGenerator.getRandom(0, cellBitmaps.length)]);
            }
        }
        fence = BitmapUtil.getScaledBitmap(context, 40, 60, R.drawable.fence);
    }

    @Override
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
            canvas.drawBitmap(fence, display.offsetX(0), display.offsetY(i * 40), null);
            canvas.drawBitmap(fence, display.offsetX((level.fieldSize - 1) * 40), display.offsetY(i * 40), null);
            canvas.drawBitmap(fence, display.offsetX(i * 40), display.offsetY(0), null);
            canvas.drawBitmap(fence, display.offsetX(i * 40), display.offsetY((level.fieldSize - 1) * 40), null);
        }
        chestController.draw(canvas, display);
        for (GameObject object : level.objects) {
            if (hero.getDistance(object) < 100) {
                actionController.draw(canvas);
                break;
            }
        }
    }

    public void onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN && actionController.clickOnAction(event.getX(), event.getY())) {
            for (IntractableGameObject object : level.objects) {
                if (hero.getDistance(object) < 100) {
                    object.action();
                }
            }
        }
    }

    public Set<Chest> getRandomChests() {
        Set<Chest> chests = new HashSet<>();
        chests.add(new Chest(20, 20, new Lockpick(3), 1, ChestType.Weapon));
        chests.add(new Chest(200, 200, new Lockpick(3), 5, ChestType.Ordinary));
        chests.add(new Chest(600, 600, new Lockpick(3), 10, ChestType.Rare));
        return chests;
    }
}
