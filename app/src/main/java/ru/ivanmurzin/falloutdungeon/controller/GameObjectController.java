package ru.ivanmurzin.falloutdungeon.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import ru.ivanmurzin.falloutdungeon.Constants;
import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.controller.object.BulletController;
import ru.ivanmurzin.falloutdungeon.controller.object.ChestController;
import ru.ivanmurzin.falloutdungeon.controller.object.unit.UnitController;
import ru.ivanmurzin.falloutdungeon.lib.InteractiveGameObject;
import ru.ivanmurzin.falloutdungeon.lib.game.Level;
import ru.ivanmurzin.falloutdungeon.lib.game.object.GameItem;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.Chest;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Cryolator;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.LaserPistol;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Pistol;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.lib.item.lockpick.Lockpick;
import ru.ivanmurzin.falloutdungeon.lib.unit.Unit;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class GameObjectController {
    private final Level level;
    private final UnitController unitController;
    private final ChestController chestController;
    private final BulletController bulletController;
    private final ItemController itemController;

    public GameObjectController(Context context, Level level) {
        this.level = level;
        unitController = new UnitController(context);
        itemController = new ItemController(context);
        bulletController = new BulletController(context);
        chestController = new ChestController(context, level);
    }

    public void draw(Canvas canvas, GameDisplay display) {
        Log.d(Constants.TAG_D, level.getInteractiveGameObjects().size() + "");
        for (InteractiveGameObject interactive : level.getInteractiveGameObjects()) {
            if (interactive instanceof Chest) {
                chestController.draw(canvas, display, (Chest) interactive);
                continue;
            }
            if (interactive instanceof GameItem) {
                itemController.draw(canvas, display, (GameItem) interactive);
            }
        }
        for (Weapon.Bullet bullet : level.getBullets()) {
            bulletController.draw(canvas, display, bullet);
        }
        for (Unit unit : level.getUnits()) {
            unitController.draw(canvas, display, unit);
        }
    }

    private static class ItemController {
        private final Bitmap lockpickBitmap;
        private final Bitmap weaponBitmap;
        private final Bitmap pistolBitmap;
        private final Bitmap laserPistolBitmap;
        private final Bitmap cryolatorBitmap;
        private final Bitmap unknownBitmap;

        public ItemController(Context context) {
            lockpickBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.lockpick);
            weaponBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.weapon);
            pistolBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.pistol);
            laserPistolBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.laser_pistol);
            cryolatorBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.cryolator);
            unknownBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.unknown);
        }

        public void draw(Canvas canvas, GameDisplay display, GameItem gameItem) {
            Item item = gameItem.item;
            Bitmap itemBitmap;
            if (item instanceof Lockpick) {
                itemBitmap = lockpickBitmap;
            } else if (item instanceof Pistol) {
                itemBitmap = pistolBitmap;
            } else if (item instanceof LaserPistol) {
                itemBitmap = laserPistolBitmap;
            } else if (item instanceof Cryolator) {
                itemBitmap = cryolatorBitmap;
            } else if (item instanceof Weapon) {
                itemBitmap = weaponBitmap;
            } else {
                itemBitmap = unknownBitmap;
            }
            canvas.drawBitmap(itemBitmap, display.offsetX(gameItem.x), display.offsetY(gameItem.y), null);
        }
    }
}
