package ru.ivanmurzin.falloutdungeon.controller.object;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import java.util.HashSet;
import java.util.Set;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.controller.GameObjectController;
import ru.ivanmurzin.falloutdungeon.controller.object.unit.HeroController;
import ru.ivanmurzin.falloutdungeon.controller.ui.JoystickController;
import ru.ivanmurzin.falloutdungeon.lib.game.Level;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.Chest;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.ChestType;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Cryolator;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.LaserPistol;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Pistol;
import ru.ivanmurzin.falloutdungeon.lib.item.lockpick.Lockpick;
import ru.ivanmurzin.falloutdungeon.lib.unit.Unit;
import ru.ivanmurzin.falloutdungeon.lib.unit.enemy.Raider;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class LevelController {
    private final GameObjectController gameObjectController;
    private final HeroController heroController;
    private final Level level;
    private final Bitmap defaultBitmap;
    private final Bitmap fence;

    public LevelController(Context context, int width, int height) {
        level = new Level(1, 40);
        heroController = new HeroController(context, level, width, height, 40);
        gameObjectController = new GameObjectController(context, level);
        Bitmap[] cellBitmaps = new Bitmap[4];
        cellBitmaps[0] = BitmapUtil.getScaledBitmap(context, 40, 40, R.drawable.tile1);
        cellBitmaps[1] = BitmapUtil.getScaledBitmap(context, 40, 40, R.drawable.tile2);
        cellBitmaps[2] = BitmapUtil.getScaledBitmap(context, 40, 40, R.drawable.tile3);
        cellBitmaps[3] = BitmapUtil.getScaledBitmap(context, 40, 40, R.drawable.tile4);
        defaultBitmap = BitmapUtil.getScaledBitmap(context, 40, 40, R.drawable.tile1);
        for (int i = 1; i < level.cells.length - 1; i++) {
            for (int j = 1; j < level.cells[i].length - 1; j++) {
                level.cells[i][j].setBitmap(cellBitmaps[RandomGenerator.getRandom(0, cellBitmaps.length)]);
            }
        }
        fence = BitmapUtil.getScaledBitmap(context, 40, 60, R.drawable.fence);
        getRandomChests().forEach(level::addChest);
        getRandomUnits().forEach(level::addUnit);
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
            canvas.drawBitmap(fence, display.offsetX(0), display.offsetY(i * 40), null);
            canvas.drawBitmap(fence, display.offsetX((level.fieldSize - 1) * 40), display.offsetY(i * 40), null);
            canvas.drawBitmap(fence, display.offsetX(i * 40), display.offsetY(0), null);
            canvas.drawBitmap(fence, display.offsetX(i * 40), display.offsetY((level.fieldSize - 1) * 40), null);
        }

        gameObjectController.draw(canvas, display);
        heroController.draw(canvas, display);
    }


    private Set<Chest> getRandomChests() {
        Set<Chest> chests = new HashSet<>();
        chests.add(new Chest(20, 20, new Lockpick(3), 1, ChestType.Ordinary));
        chests.add(new Chest(100, 100, new Pistol(), 0, ChestType.Weapon));
        chests.add(new Chest(200, 200, new LaserPistol(), 0, ChestType.Weapon));
        chests.add(new Chest(700, 700, new Cryolator(), 5, ChestType.Weapon));
        return chests;
    }

    private Set<Unit> getRandomUnits() {
        Set<Unit> units = new HashSet<>();
        units.add(new Raider(200, 200, level));
        units.add(new Raider(50, 50, level));
        units.add(new Raider(850, 850, level));
        return units;
    }

    public void update() {
        heroController.update();
        level.update();
    }

    public JoystickController getJoystickController() {
        return heroController.joystickController;
    }

    public void onTouchEvent(MotionEvent event) {
        heroController.onTouchEvent(event);
    }
}
