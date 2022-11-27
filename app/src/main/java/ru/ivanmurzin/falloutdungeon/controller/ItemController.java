package ru.ivanmurzin.falloutdungeon.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.lib.item.lockpick.Lockpick;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class ItemController {
    private final Bitmap lockpickBitmap;
    private final Bitmap weaponBitmap;

    public ItemController(Context context) {
        lockpickBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.lockpick);
        weaponBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.weapon);
    }

    public void draw(Canvas canvas, GameDisplay display, Item item, int x, int y) {
        Bitmap itemBitmap;
        if (item instanceof Lockpick) {
            itemBitmap = lockpickBitmap;
        } else if (item instanceof Weapon) {
            itemBitmap = weaponBitmap;
        } else {
            itemBitmap = lockpickBitmap;
        }
        canvas.drawBitmap(itemBitmap, display.offsetX(x), display.offsetY(y), null);
    }
}
