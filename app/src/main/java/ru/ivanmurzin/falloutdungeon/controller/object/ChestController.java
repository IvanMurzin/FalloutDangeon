package ru.ivanmurzin.falloutdungeon.controller.object;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Set;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.controller.ItemController;
import ru.ivanmurzin.falloutdungeon.lib.game.Level;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.Chest;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class ChestController extends DrawController {

    private final ItemController itemController;
    private final Level level;
    private final Bitmap chestMetal;
    private final Bitmap chestGreen;
    private final Bitmap chestWood;
    private final Set<Chest> chests;

    public ChestController(Context context, Set<Chest> chests, Level level) {
        itemController = new ItemController(context);
        this.level = level;
        chestMetal = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.chest_metal);
        chestGreen = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.chest_green);
        chestWood = BitmapUtil.getScaledBitmap(context, 100, 80, R.drawable.chest_poor);
        this.chests = chests;
    }

    @Override
    public void draw(Canvas canvas, GameDisplay display) {
        for (Chest chest : chests) {
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
                    itemController.draw(canvas, display, chest.item, chest.x, chest.y);
                    break;
                case Clear:
                    level.objects.remove(chest);
                    chests.remove(chest);
            }
        }
    }
}
