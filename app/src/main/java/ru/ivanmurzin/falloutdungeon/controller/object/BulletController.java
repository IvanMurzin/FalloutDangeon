package ru.ivanmurzin.falloutdungeon.controller.object;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class BulletController {
    private final Bitmap unknownBitmap;
    private final Bitmap bulletNBitmap;
    private final Bitmap bulletSBitmap;
    private final Bitmap bulletWBitmap;
    private final Bitmap bulletEBitmap;

    public BulletController(Context context) {
        unknownBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.unknown);
        bulletNBitmap = BitmapUtil.getScaledBitmap(context, 30, 60, R.drawable.bullet_up);
        bulletSBitmap = BitmapUtil.getScaledBitmap(context, 30, 60, R.drawable.bullet_down);
        bulletWBitmap = BitmapUtil.getScaledBitmap(context, 60, 30, R.drawable.bullet_left);
        bulletEBitmap = BitmapUtil.getScaledBitmap(context, 60, 30, R.drawable.bullet_right);
    }

    public void draw(Canvas canvas, GameDisplay display, Weapon.Bullet bullet) {
        Bitmap bulletBitmap;
        switch (bullet.direction) {
            case South:
                bulletBitmap = bulletSBitmap;
                break;
            case North:
                bulletBitmap = bulletNBitmap;
                break;
            case West:
                bulletBitmap = bulletWBitmap;
                break;
            default:
                bulletBitmap = bulletEBitmap;
                break;
        }
        canvas.drawBitmap(bulletBitmap, display.offsetX(bullet.x), display.offsetY(bullet.y), null);

    }
}
