package ru.ivanmurzin.falloutdungeon.controller.object;

import static ru.ivanmurzin.falloutdungeon.lib.game.Level.TILE_SIZE;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import java.util.LinkedList;
import java.util.List;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.controller.adapter.GameObjectAdapter;
import ru.ivanmurzin.falloutdungeon.controller.object.unit.HeroController;
import ru.ivanmurzin.falloutdungeon.controller.ui.JoystickController;
import ru.ivanmurzin.falloutdungeon.lib.game.Level;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.Chest;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.ChestType;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.ItemGenerator;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponGenerator;
import ru.ivanmurzin.falloutdungeon.lib.unit.Unit;
import ru.ivanmurzin.falloutdungeon.lib.unit.enemy.Raider;
import ru.ivanmurzin.falloutdungeon.lib.unit.enemy.SuperMutant;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class LevelController {
    private final ItemGenerator generator;
    private final GameObjectAdapter gameObjectAdapter;
    private final HeroController heroController;
    private final Level level;
    private final Bitmap defaultBitmap;
    private final Bitmap fence;

    public LevelController(Context context, int width, int height) {
        level = new Level(1, 40);
        heroController = new HeroController(context, level, width, height, TILE_SIZE);
        gameObjectAdapter = new GameObjectAdapter(context, level);
        generator = new ItemGenerator(context);
        getRandomChests().forEach(level::addChest);
        getRandomUnits().forEach(level::addUnit);

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


    private List<Chest> getRandomChests() {
        List<Chest> chests = new LinkedList<>();
        chests.add(new Chest(200, 200, WeaponGenerator.getSimpleWeapon(), 1, ChestType.Weapon));
        chests.add(new Chest(400, 200, WeaponGenerator.getMiddleWeapon(), 2, ChestType.Weapon));
        chests.add(new Chest(200, 400, WeaponGenerator.getMiddleWeapon(), 3, ChestType.Weapon));
        chests.add(new Chest(400, 400, WeaponGenerator.getTopWeapon(), 4, ChestType.Weapon));

        chests.add(new Chest(1000, 200, generator.getArmor(), 1, ChestType.Rare));
        chests.add(new Chest(1200, 200, generator.getArmor(), 2, ChestType.Rare));
        chests.add(new Chest(1000, 400, generator.getArmor(), 3, ChestType.Rare));
        chests.add(new Chest(1200, 400, generator.getArmor(), 4, ChestType.Rare));

        chests.add(new Chest(200, 800, generator.getSimpleItem(), 1, ChestType.Ordinary));
        chests.add(new Chest(400, 800, generator.getSimpleItem(), 2, ChestType.Ordinary));
        chests.add(new Chest(200, 1000, generator.getSimpleItem(), 3, ChestType.Ordinary));
        chests.add(new Chest(400, 1000, generator.getSimpleItem(), 4, ChestType.Ordinary));

        return chests;
    }

    private List<Unit> getRandomUnits() {
        List<Unit> units = new LinkedList<>();
        units.add(new Raider(level.fieldSize * TILE_SIZE - 100, level.fieldSize * TILE_SIZE - 100, level));
        units.add(new Raider(50, 50, level));
        units.add(new Raider(850, 850, level));
        units.add(new SuperMutant(550, 550, level));
        return units;
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
