package ru.ivanmurzin.falloutdungeon.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.controller.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.lib.game.object.GameObject;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;


public class HealthBar extends GameObject {
    private final Paint paintText = new Paint();
    private final Paint paintHealth = new Paint();

    public HealthBar(Context context) {
        super(0, 0, BitmapUtil.getScaledBitmap(context, 450, 150, R.drawable.health_bar));
        paintHealth.setColor(Color.parseColor("#FA0000"));
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(40);
    }

    public void draw(Canvas canvas) {
        Hero hero = Hero.instance;
        float healthScale = hero.getHealth() / (float) hero.getMaxHealth();
        canvas.drawRect(90, 59, 90 + 270 * healthScale, 86, paintHealth);
        canvas.drawBitmap(bitmap, x, y, null);
        canvas.drawText(hero.getHealth() + "/" + hero.getMaxHealth(), 380, 85, paintText);
    }
}
