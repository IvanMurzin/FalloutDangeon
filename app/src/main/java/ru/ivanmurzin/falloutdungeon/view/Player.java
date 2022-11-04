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
    private float speedX;
    private float speedY;


    public Player(Context context, int width, int height) {
        super(width / 2, height / 2, null);
        int heroHeight = 200;
        int heroWidth = 200;
        heroRight = BitmapUtil.getScaledBitmap(context, heroWidth, heroHeight, R.drawable.hero_right);
        heroLeft = BitmapUtil.getScaledBitmap(context, heroWidth, heroHeight, R.drawable.hero_left);
        hero = new Hero();
        joystick = new Joystick(context, width / 8f, height * 3 / 4f, width / 9f - 25, width / 9f);
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
    }
}
