package ru.ivanmurzin.falloutdungeon.controller.object.unit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.lib.unit.Unit;
import ru.ivanmurzin.falloutdungeon.lib.unit.enemy.Raider;
import ru.ivanmurzin.falloutdungeon.lib.unit.enemy.SuperMutant;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class UnitController {
    private final Bitmap unknownBitmap;
    private final Bitmap raiderBitmap;
    private final Bitmap superMutantBitmap;


    public UnitController(Context context) {
        unknownBitmap = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.unknown);
        raiderBitmap = BitmapUtil.getScaledBitmap(context, 100, 150, R.drawable.raider);
        superMutantBitmap = BitmapUtil.getScaledBitmap(context, 100, 150, R.drawable.supermutant);
    }

    public void draw(Canvas canvas, GameDisplay display, Unit unit) {
        Bitmap unitBitmap;
        if (unit instanceof Hero) return;
        if (unit instanceof Raider) {
            unitBitmap = raiderBitmap;
        } else if (unit instanceof SuperMutant) {
            unitBitmap = superMutantBitmap;
        } else {
            unitBitmap = unknownBitmap;
        }
        canvas.drawBitmap(unitBitmap, display.offsetX(unit.x), display.offsetY(unit.y), null);
    }
}
