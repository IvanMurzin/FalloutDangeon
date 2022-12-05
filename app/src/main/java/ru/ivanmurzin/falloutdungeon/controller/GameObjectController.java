package ru.ivanmurzin.falloutdungeon.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.controller.object.BulletController;
import ru.ivanmurzin.falloutdungeon.controller.object.ChestController;
import ru.ivanmurzin.falloutdungeon.controller.object.unit.UnitController;
import ru.ivanmurzin.falloutdungeon.lib.InteractiveGameObject;
import ru.ivanmurzin.falloutdungeon.lib.game.Level;
import ru.ivanmurzin.falloutdungeon.lib.game.object.GameItem;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.Chest;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.item.aid.CommonAid;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.LeatherBreastplate;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.LeatherHelmet;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.MetalBreastplate;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.MetalHelmet;
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
        private final Bitmap leatherHelmetBitmap;
        private final Bitmap metalHelmetBitmap;
        private final Bitmap leatherBreastplateBitmap;
        private final Bitmap metalBreastplateBitmap;
        private final Bitmap stimpackBitmap;
        private final Bitmap unknownBitmap;

        public ItemController(Context context) {
            lockpickBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.lockpick);
            weaponBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.weapon);
            pistolBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.pistol);
            laserPistolBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.laser_pistol);
            cryolatorBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.cryolator);
            leatherHelmetBitmap = BitmapUtil.getScaledBitmap(context, 80, 80, R.drawable.leather_helmet);
            metalHelmetBitmap = BitmapUtil.getScaledBitmap(context, 80, 50, R.drawable.metal_helmet);
            leatherBreastplateBitmap = BitmapUtil.getScaledBitmap(context, 80, 80, R.drawable.leather_breatplate);
            metalBreastplateBitmap = BitmapUtil.getScaledBitmap(context, 80, 80, R.drawable.metal_breastplate);
            stimpackBitmap = BitmapUtil.getScaledBitmap(context, 60, 80, R.drawable.stimpack);
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
            } else if (item instanceof LeatherHelmet) {
                itemBitmap = leatherHelmetBitmap;
            } else if (item instanceof LeatherBreastplate) {
                itemBitmap = leatherBreastplateBitmap;
            } else if (item instanceof MetalHelmet) {
                itemBitmap = metalHelmetBitmap;
            } else if (item instanceof MetalBreastplate) {
                itemBitmap = metalBreastplateBitmap;
            } else if (item instanceof CommonAid) {
                itemBitmap = stimpackBitmap;
            } else if (item instanceof Weapon) {
                itemBitmap = weaponBitmap;
            } else {
                itemBitmap = unknownBitmap;
            }
            canvas.drawBitmap(itemBitmap, display.offsetX(gameItem.x), display.offsetY(gameItem.y), null);
        }
    }
}
