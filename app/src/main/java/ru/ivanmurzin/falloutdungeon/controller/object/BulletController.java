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
    private final Bitmap bulletOrdinaryN;
    private final Bitmap bulletOrdinaryS;
    private final Bitmap bulletOrdinaryW;
    private final Bitmap bulletOrdinaryE;
    private final Bitmap bulletLaserV;
    private final Bitmap bulletLaserH;

    public BulletController(Context context) {
        unknownBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.unknown);
        bulletOrdinaryN = BitmapUtil.getScaledBitmap(context, 30, 60, R.drawable.bullet_up);
        bulletOrdinaryS = BitmapUtil.getScaledBitmap(context, 30, 60, R.drawable.bullet_down);
        bulletOrdinaryW = BitmapUtil.getScaledBitmap(context, 60, 30, R.drawable.bullet_left);
        bulletOrdinaryE = BitmapUtil.getScaledBitmap(context, 60, 30, R.drawable.bullet_right);
        bulletLaserV = BitmapUtil.getScaledBitmap(context, 30, 60, R.drawable.laser_bullet_vertical);
        bulletLaserH = BitmapUtil.getScaledBitmap(context, 60, 30, R.drawable.laser_bullet_horizontal);
    }

    public void draw(Canvas canvas, GameDisplay display, Weapon.Bullet bullet) {
        Bitmap bulletBitmap;
        switch (bullet.getType()) {
            case Laser:
                bulletBitmap = getLaserBulletBitmap(bullet.direction);
                break;
            default:
                bulletBitmap = getOrdinaryBulletBitmap(bullet.direction);
        }
        canvas.drawBitmap(bulletBitmap, display.offsetX(bullet.x), display.offsetY(bullet.y), null);
    }

    private Bitmap getOrdinaryBulletBitmap(Weapon.Direction direction) {
        switch (direction) {
            case South:
                return bulletOrdinaryS;
            case North:
                return bulletOrdinaryN;
            case West:
                return bulletOrdinaryW;
            default:
                return bulletOrdinaryE;
        }
    }

    private Bitmap getLaserBulletBitmap(Weapon.Direction direction) {
        switch (direction) {
            case North:
            case South:
                return bulletLaserV;
            default:
                return bulletLaserH;
        }
    }
}
