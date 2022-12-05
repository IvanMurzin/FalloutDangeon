package ru.ivanmurzin.falloutdungeon.controller.object;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class BulletController {
    private final Bitmap bulletOrdinaryN;
    private final Bitmap bulletOrdinaryS;
    private final Bitmap bulletOrdinaryW;
    private final Bitmap bulletOrdinaryE;
    private final Bitmap bulletFrostN;
    private final Bitmap bulletFrostS;
    private final Bitmap bulletFrostW;
    private final Bitmap bulletFrostE;
    private final Bitmap bulletLaserV;
    private final Bitmap bulletLaserH;

    public BulletController(Context context) {
        bulletOrdinaryN = BitmapUtil.getScaledBitmap(context, 15, 40, R.drawable.bullet_up);
        bulletOrdinaryS = BitmapUtil.getScaledBitmap(context, 15, 40, R.drawable.bullet_down);
        bulletOrdinaryW = BitmapUtil.getScaledBitmap(context, 40, 15, R.drawable.bullet_left);
        bulletOrdinaryE = BitmapUtil.getScaledBitmap(context, 40, 15, R.drawable.bullet_right);
        bulletFrostN = BitmapUtil.getScaledBitmap(context, 30, 60, R.drawable.frost_bullet_up);
        bulletFrostS = BitmapUtil.getScaledBitmap(context, 30, 60, R.drawable.frost_bullet_down);
        bulletFrostW = BitmapUtil.getScaledBitmap(context, 60, 30, R.drawable.frost_bullet_left);
        bulletFrostE = BitmapUtil.getScaledBitmap(context, 60, 30, R.drawable.frost_bullet_right);
        bulletLaserV = BitmapUtil.getScaledBitmap(context, 30, 60, R.drawable.laser_bullet_vertical);
        bulletLaserH = BitmapUtil.getScaledBitmap(context, 60, 30, R.drawable.laser_bullet_horizontal);
    }

    public void draw(Canvas canvas, GameDisplay display, Weapon.Bullet bullet) {
        Bitmap bulletBitmap;
        switch (bullet.getType()) {
            case Laser:
                bulletBitmap = getLaserBulletBitmap(bullet.direction);
                break;
            case Frost:
                bulletBitmap = getFrostBulletBitmap(bullet.direction);
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

    private Bitmap getFrostBulletBitmap(Weapon.Direction direction) {
        switch (direction) {
            case South:
                return bulletFrostS;
            case North:
                return bulletFrostN;
            case West:
                return bulletFrostW;
            default:
                return bulletFrostE;
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
