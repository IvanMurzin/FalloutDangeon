package ru.ivanmurzin.falloutdungeon.controller.object.unit;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.Locale;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.controller.ActionController;
import ru.ivanmurzin.falloutdungeon.controller.Logger;
import ru.ivanmurzin.falloutdungeon.controller.NotifyController;
import ru.ivanmurzin.falloutdungeon.controller.adapter.HealthBarAdapter;
import ru.ivanmurzin.falloutdungeon.controller.adapter.JoystickAdapter;
import ru.ivanmurzin.falloutdungeon.lib.GameObject;
import ru.ivanmurzin.falloutdungeon.lib.InteractiveGameObject;
import ru.ivanmurzin.falloutdungeon.lib.game.Field;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class HeroController {
    private static final int ACTION_ACTIVATION_RADIUS = 100;
    public final JoystickAdapter joystickAdapter;
    public final HealthBarAdapter healthBarAdapter;
    private final Logger logger;
    private final Field field;
    private final ActionController actionController;
    private final ActionController shootController;
    private final Frames framesNE;
    private final Frames framesE;
    private final Frames framesSE;
    private final Frames framesSW;
    private final Frames framesW;
    private final Frames framesNW;
    private Bitmap currentFrame;
    private float speedX;
    private float speedY;
    private float lastSpeedX;
    private float lastSpeedY;
    private int reload = 0;

    public HeroController(Context context, Field field, int width, int height) {
        this.field = field;
        Hero.instance.x = width / 2f;
        Hero.instance.y = height / 2f;
        joystickAdapter = new JoystickAdapter(context, 250, height * 3 / 4f);
        healthBarAdapter = new HealthBarAdapter(context);
        actionController = new ActionController(context, width - 400, height - 300, R.drawable.act);
        shootController = new ActionController(context, width - 200, height - 200, R.drawable.shoot);
        logger = new NotifyController(context);

        int heroHeight = 150;
        int heroWidth = 120;

        framesNE = new Frames(context, 0, 7, heroWidth, heroHeight);
        framesE = new Frames(context, 8, 15, heroWidth, heroHeight);
        framesSE = new Frames(context, 16, 23, heroWidth, heroHeight);
        framesSW = new Frames(context, 24, 31, heroWidth, heroHeight);
        framesW = new Frames(context, 32, 39, heroWidth, heroHeight);
        framesNW = new Frames(context, 40, 47, heroWidth, heroHeight);
        currentFrame = setFrame();
    }

    public void draw(Canvas canvas, GameDisplay display) {
        canvas.drawBitmap(currentFrame, display.offsetX(Hero.instance.x), display.offsetY(Hero.instance.y), null);
        joystickAdapter.draw(canvas);
        healthBarAdapter.draw(canvas);
        for (GameObject object : field.getLevel().getInteractiveGameObjects()) {
            if (Hero.instance.getDistance(object.x, object.y) < ACTION_ACTIVATION_RADIUS) {
                actionController.draw(canvas);
                break;
            }
        }
        if (reload == 0) shootController.draw(canvas);
    }

    public void update() {
        joystickAdapter.controller.update();
        if (speedX != 0 && speedY != 0) {
            lastSpeedX = speedX;
            lastSpeedY = speedY;
        }
        speedX = joystickAdapter.controller.getActuatorX() * Hero.instance.getSpeed();
        speedY = joystickAdapter.controller.getActuatorY() * Hero.instance.getSpeed();
        Hero.instance.x += speedX;
        Hero.instance.y += speedY;
        if (Hero.instance.x > (field.getLevel().fieldSize - 3) * 40)
            Hero.instance.x = (field.getLevel().fieldSize - 3) * 40;
        if (Hero.instance.x < 0) {
            Hero.instance.x = 0;
        }
        if (Hero.instance.y > (field.getLevel().fieldSize - 5) * 40)
            Hero.instance.y = (field.getLevel().fieldSize - 5) * 40;
        if (Hero.instance.y < 0) Hero.instance.y = 0;
        currentFrame = setFrame();
        if (reload != 0) reload = (reload + 1) % Hero.instance.getWeapon().reloadTime;
        if (reload != 0) reload = (reload + 1) % Hero.instance.getWeapon().reloadTime;
    }

    private Bitmap setFrame() {
        if (speedX > 0 && speedY < -5) return framesNE.getCurrentFrame();
        if (speedX > 0 && (speedY >= -5 && speedY <= 5)) return framesE.getCurrentFrame();
        if (speedX > 0 && speedY > 5) return framesSE.getCurrentFrame();
        if (speedX < 0 && speedY > 5) return framesSW.getCurrentFrame();
        if (speedX < 0 && speedY >= -5) return framesW.getCurrentFrame();
        if (speedX < 0 && speedY < -5) return framesNW.getCurrentFrame();
        framesSW.reset();
        return framesSW.getCurrentFrame();
    }

    public void onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() != MotionEvent.ACTION_POINTER_DOWN && event.getActionMasked() != MotionEvent.ACTION_DOWN)
            return;
        if (actionController.clickOnAction(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()))) {
            for (InteractiveGameObject object : field.getLevel().getInteractiveGameObjects()) {
                if (Hero.instance.getDistance(object.x, object.y) < ACTION_ACTIVATION_RADIUS) {
                    object.action(logger);
                    break;
                }
            }
            return;
        }

        if (reload == 0 && shootController.clickOnAction(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()))) {
            if (speedX == 0 && speedY == 0) {
                field.getLevel().addBullet(Hero.instance.shoot(lastSpeedX, lastSpeedY));
            } else {
                field.getLevel().addBullet(Hero.instance.shoot(speedX, speedY));
            }
            reload++;
        }
    }

    private static class Frames {
        public final Bitmap[] frames = new Bitmap[8];
        public int current = 0;

        Frames(Context context, int from, int to, int width, int height) {
            for (int i = from; i <= to; i++) {
                String name = "tile0" + String.format(Locale.ROOT, "%02d", i);
                int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
                frames[i % frames.length] = BitmapUtil.getScaledBitmap(context, width, height, id);
            }
        }

        public void reset() {
            current = 0;
        }

        public Bitmap getCurrentFrame() {
            Bitmap frame = frames[current];
            current = (current + 1) % frames.length;
            return frame;
        }
    }

}
