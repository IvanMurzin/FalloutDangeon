package ru.ivanmurzin.falloutdungeon.controller.object;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.lib.game.Field;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.Chest;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.ChestState;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class ChestController {
    private final Field field;
    private final Bitmap chestMetal;
    private final Bitmap chestGreen;
    private final Bitmap chestWood;

    public ChestController(Context context, Field field) {
        this.field = field;
        chestMetal = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.chest_metal);
        chestGreen = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.chest_green);
        chestWood = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.chest_poor);
    }

    public void draw(Canvas canvas, GameDisplay display, Chest chest) {
        switch (chest.getState()) {
            case Closed:
                Bitmap chestBitmap;
                switch (chest.type) {
                    case Weapon:
                        chestBitmap = chestMetal;
                        break;
                    case Rare:
                        chestBitmap = chestGreen;
                        break;
                    default:
                        chestBitmap = chestWood;
                }
                canvas.drawBitmap(chestBitmap, display.offsetX(chest.x), display.offsetY(chest.y), null);
                break;
            case Opened:
                field.getLevel().removeInteractive(chest);
                field.getLevel().addDroppedItem(chest.item, chest.x, chest.y);
                chest.setState(ChestState.Removed);
        }
    }
}
