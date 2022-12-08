package ru.ivanmurzin.falloutdungeon.controller.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.controller.ui.UiController;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;

public class HealthBarAdapter implements UiController {

    private final Bitmap bitmap;
    private final Paint paintText = new Paint();
    private final Paint paintHealth = new Paint();

    public HealthBarAdapter(Context context) {
        bitmap = BitmapUtil.getScaledBitmap(context, 450, 150, R.drawable.health_bar);
        paintHealth.setColor(Color.parseColor("#FA0000"));
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(40);
    }

    @Override
    public void draw(Canvas canvas) {
        Hero hero = Hero.instance;
        double healthScale = hero.getHealth() / hero.getMaxHealth();
        canvas.drawRect(90, 59, (float) (90 + 270 * healthScale), 86, paintHealth);
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawText((int) hero.getHealth() + "/" + (int) hero.getMaxHealth(), 380, 85, paintText);
    }
}
