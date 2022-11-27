package ru.ivanmurzin.falloutdungeon.controller.object;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Set;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.lib.game.Level;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.Chest;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class ChestController extends DrawController {

    private final Bitmap chestMetal;
    private final Bitmap chestGreen;
    private final Bitmap chestWood;
    private final Set<Chest> chests;

    public ChestController(Context context, Level level, Set<Chest> chests) {
        chestMetal = BitmapUtil.getScaledBitmap(context, 100, 50, R.drawable.chest_metal);
        chestGreen = BitmapUtil.getScaledBitmap(context, 100, 50, R.drawable.chest_green);
        chestWood = BitmapUtil.getScaledBitmap(context, 100, 50, R.drawable.chest_poor);
        this.chests = chests;
    }

    @Override
    public void draw(Canvas canvas, GameDisplay display) {
        for (Chest chest : chests) {
            canvas.drawBitmap(chestMetal, display.offsetX(chest.x), display.offsetY(chest.y), null);
        }
    }
}
