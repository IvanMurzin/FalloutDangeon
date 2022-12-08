package ru.ivanmurzin.falloutdungeon.controller.object;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.lib.game.Level;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.Chest;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.ChestState;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class ChestController {
    private final Level level;
    private final Bitmap chestMetal;
    private final Bitmap chestGreen;
    private final Bitmap chestWood;

    public ChestController(Context context, Level level) {
        this.level = level;
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
                level.removeInteractive(chest);
                level.addDroppedItem(chest.item, chest.x, chest.y);
                chest.setState(ChestState.Removed);
        }
    }
}
