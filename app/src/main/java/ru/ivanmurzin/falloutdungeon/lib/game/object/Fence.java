package ru.ivanmurzin.falloutdungeon.lib.game.object;

import android.content.Context;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.controller.BitmapUtil;

public class Fence extends Cell {
    public Fence(Context context, int x, int y) {
        super(x, y, BitmapUtil.getScaledBitmap(context, 40, 60, R.drawable.fence));
    }
}
