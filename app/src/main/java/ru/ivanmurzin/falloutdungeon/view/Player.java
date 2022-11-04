package ru.ivanmurzin.falloutdungeon.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.controller.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.controller.Joystick;
import ru.ivanmurzin.falloutdungeon.lib.game.object.GameObject;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;

public class Player extends GameObject {
    public final Hero hero;
    public final Joystick joystick;
    public final Bitmap heroRight;
    public final Bitmap heroLeft;
    private static final float speed = 30;
    private final int fieldSize;
    private float speedX;
    private float speedY;


    public Player(Context context, int width, int height, int fieldSize) {
        super(width / 2, height / 2, null);
        this.fieldSize = fieldSize;
        int heroHeight = 200;
        int heroWidth = 180;
        heroRight = BitmapUtil.getScaledBitmap(context, heroWidth, heroHeight, R.drawable.hero_right);
        heroLeft = BitmapUtil.getScaledBitmap(context, heroWidth, heroHeight, R.drawable.hero_left);
        hero = new Hero();
        joystick = new Joystick(context, 250, height * 3 / 4f, 100, 150);
    }

    @Override
    public void draw(Canvas canvas, GameDisplay display) {
        Bitmap heroBitmap = speedX < 0 ? heroLeft : heroRight;
        canvas.drawBitmap(heroBitmap, display.offsetX(x), display.offsetY(y), null);
        joystick.draw(canvas);
    }

    public void update() {
        joystick.update();
        speedX = joystick.getActuatorX() * speed;
        speedY = joystick.getActuatorY() * speed;
        x += speedX;
        y += speedY;
        if (x > (fieldSize - 5) * 40) x = (fieldSize - 5) * 40;
        if (x < 0) x = 0;
        if (y > (fieldSize - 5) * 40) y = (fieldSize - 5) * 40;
        if (y < 0) y = 0;
    }
}
