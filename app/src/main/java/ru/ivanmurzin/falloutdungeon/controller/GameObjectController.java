package ru.ivanmurzin.falloutdungeon.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.controller.object.ChestController;
import ru.ivanmurzin.falloutdungeon.lib.GameObject;
import ru.ivanmurzin.falloutdungeon.lib.game.Level;
import ru.ivanmurzin.falloutdungeon.lib.game.object.GameItem;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.Chest;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.LaserPistol;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Pistol;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.lib.item.lockpick.Lockpick;
import ru.ivanmurzin.falloutdungeon.lib.unit.enemy.Raider;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class GameObjectController {
    private final ChestController chestController;
    private final Bitmap unknownBitmap;
    private final Bitmap bulletNBitmap;
    private final Bitmap bulletSBitmap;
    private final Bitmap bulletWBitmap;
    private final Bitmap bulletEBitmap;
    private final Bitmap raiderBitmap;
    private final ItemController itemController;

    public GameObjectController(Context context, Level level) {
        itemController = new ItemController(context);
        chestController = new ChestController(context, level);
        unknownBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.unknown);
        bulletNBitmap = BitmapUtil.getScaledBitmap(context, 30, 60, R.drawable.bullet_up);
        bulletSBitmap = BitmapUtil.getScaledBitmap(context, 30, 60, R.drawable.bullet_down);
        bulletWBitmap = BitmapUtil.getScaledBitmap(context, 60, 30, R.drawable.bullet_left);
        bulletEBitmap = BitmapUtil.getScaledBitmap(context, 60, 30, R.drawable.bullet_right);
        raiderBitmap = BitmapUtil.getScaledBitmap(context, 100, 150, R.drawable.raider);
    }

    public void draw(Canvas canvas, GameDisplay display, GameObject object) {
        Bitmap itemBitmap;
        if (object instanceof Chest) {
            chestController.draw(canvas, display, (Chest) object);
            return;
        }
        if (object instanceof GameItem) {
            itemController.draw(canvas, display, (GameItem) object);
            return;
        }
        if (object instanceof Weapon.Bullet) {
            switch (((Weapon.Bullet) object).direction) {
                case South:
                    itemBitmap = bulletSBitmap;
                    break;
                case North:
                    itemBitmap = bulletNBitmap;
                    break;
                case West:
                    itemBitmap = bulletWBitmap;
                    break;
                default:
                    itemBitmap = bulletEBitmap;
                    break;
            }
        } else if (object instanceof Raider) {
            itemBitmap = raiderBitmap;
        } else {
            itemBitmap = unknownBitmap;
        }
        canvas.drawBitmap(itemBitmap, display.offsetX(object.x), display.offsetY(object.y), null);
    }

    private static class ItemController {
        private final Bitmap lockpickBitmap;
        private final Bitmap weaponBitmap;
        private final Bitmap pistolBitmap;
        private final Bitmap laserPistolBitmap;
        private final Bitmap unknownBitmap;

        public ItemController(Context context) {
            lockpickBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.lockpick);
            weaponBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.weapon);
            pistolBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.pistol);
            laserPistolBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.laser_pistol);
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
            } else if (item instanceof Weapon) {
                itemBitmap = weaponBitmap;
            } else {
                itemBitmap = unknownBitmap;
            }
            canvas.drawBitmap(itemBitmap, display.offsetX(gameItem.x), display.offsetY(gameItem.y), null);
        }
    }
}
