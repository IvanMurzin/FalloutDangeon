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
import ru.ivanmurzin.falloutdungeon.controller.GameObjectController;
import ru.ivanmurzin.falloutdungeon.controller.Logger;
import ru.ivanmurzin.falloutdungeon.controller.NotifyController;
import ru.ivanmurzin.falloutdungeon.controller.object.unit.HeroController;
import ru.ivanmurzin.falloutdungeon.controller.ui.JoystickController;
import ru.ivanmurzin.falloutdungeon.lib.GameObject;
import ru.ivanmurzin.falloutdungeon.lib.InteractiveGameObject;
import ru.ivanmurzin.falloutdungeon.lib.game.Level;
import ru.ivanmurzin.falloutdungeon.lib.game.object.Bullet;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.Chest;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.ChestType;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.LaserPistol;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Pistol;
import ru.ivanmurzin.falloutdungeon.lib.item.lockpick.Lockpick;
import ru.ivanmurzin.falloutdungeon.lib.unit.enemy.Raider;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class LevelController implements Drawer {
    private final Logger logger;
    private final GameObjectController gameObjectController;
    private final ActionController actionController;
    private final ActionController shootController;
    private final HeroController heroController;
    private final Level level;
    private final Hero hero = Hero.instance;
    private final Bitmap defaultBitmap;
    private final Bitmap fence;

    public LevelController(Context context, int width, int height) {
        logger = new NotifyController(context);
        heroController = new HeroController(context, width, height, 40);
        actionController = new ActionController(context, width - 400, height - 300, R.drawable.act);
        shootController = new ActionController(context, width - 200, height - 200, R.drawable.shoot);
        level = new Level(context, 1, 40);
        level.addMovingObject(new Raider(200, 200, level));
        level.addMovingObject(new Raider(50, 50, level));
        level.addMovingObject(new Raider(850, 850, level));
        gameObjectController = new GameObjectController(context, level);
        Set<Chest> chests = getRandomChests();
        level.addChests(chests);
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
        for (GameObject object : level.getInteractiveGameObjects()) {
            if (hero.getDistance(object.x, object.y) < 100) {
                actionController.draw(canvas);
                break;
            }
        }
        for (GameObject object : level.getObjects()) {
            gameObjectController.draw(canvas, display, object);
        }
        shootController.draw(canvas);
        heroController.draw(canvas, display);
    }

    public void onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() != MotionEvent.ACTION_DOWN) return;
        if (actionController.clickOnAction(event.getX(), event.getY())) {
            Set<InteractiveGameObject> interactiveGameObjects = level.getInteractiveGameObjects();
            for (InteractiveGameObject object : interactiveGameObjects) {
                if (hero.getDistance(object.x, object.y) < 100) {
                    object.action(logger);
                    break;
                }
            }
            return;
        }
        if (shootController.clickOnAction(event.getX(), event.getY())) {
            level.addMovingObject(new Bullet(heroController.getSpeedX(), heroController.getSpeedY()));
        }
    }

    public Set<Chest> getRandomChests() {
        Set<Chest> chests = new HashSet<>();
        chests.add(new Chest(20, 20, new Lockpick(3), 1, ChestType.Ordinary));
        chests.add(new Chest(100, 100, new Pistol(), 0, ChestType.Weapon));
        chests.add(new Chest(200, 200, new LaserPistol(), 0, ChestType.Weapon));
        return chests;
    }

    public void update() {
        heroController.update();
        level.update();
    }

    public JoystickController getJoystickController() {
        return heroController.joystickController;
    }
}
