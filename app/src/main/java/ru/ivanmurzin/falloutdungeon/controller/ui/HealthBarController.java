package ru.ivanmurzin.falloutdungeon.controller.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;

public class HealthBarController implements UiController {

    private final Bitmap bitmap;
    private final Paint paintText = new Paint();
    private final Paint paintHealth = new Paint();

    public HealthBarController(Context context) {
        bitmap = BitmapUtil.getScaledBitmap(context, 450, 150, R.drawable.health_bar);
        paintHealth.setColor(Color.parseColor("#FA0000"));
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(40);
    }

    @Override
    public void draw(Canvas canvas) {
        Hero hero = Hero.instance;
        float healthScale = hero.getHealth() / (float) hero.getMaxHealth();
        canvas.drawRect(90, 59, 90 + 270 * healthScale, 86, paintHealth);
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawText(hero.getHealth() + "/" + hero.getMaxHealth(), 380, 85, paintText);
    }
}
