package ru.ivanmurzin.falloutdungeon.controller.object.unit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Locale;

import ru.ivanmurzin.falloutdungeon.controller.ui.HealthBarController;
import ru.ivanmurzin.falloutdungeon.controller.ui.JoystickController;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;
import ru.ivanmurzin.falloutdungeon.util.BitmapUtil;
import ru.ivanmurzin.falloutdungeon.view.GameDisplay;

public class HeroController extends UnitController {
    private static final float speed = 30;
    public final JoystickController joystickController;
    public final HealthBarController healthBarController;
    private final Frames framesNE;
    private final Frames framesE;
    private final Frames framesSE;
    private final Frames framesSW;
    private final Frames framesW;
    private final Frames framesNW;
    private final int fieldSize;
    private Bitmap currentFrame;
    private float speedX;
    private float speedY;

    public HeroController(Context context, int width, int height, int fieldSize) {
        Hero.instance.x = width / 2f;
        Hero.instance.y = height / 2f;
        this.fieldSize = fieldSize;
        joystickController = new JoystickController(context, 250, height * 3 / 4f);
        healthBarController = new HealthBarController(context);
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

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    @Override
    public void draw(Canvas canvas, GameDisplay display) {
        canvas.drawBitmap(currentFrame, display.offsetX(Hero.instance.x), display.offsetY(Hero.instance.y), null);
        joystickController.draw(canvas);
        healthBarController.draw(canvas);
    }

    @Override
    public void update() {
        joystickController.update();
        speedX = joystickController.getActuatorX() * speed;
        speedY = joystickController.getActuatorY() * speed;
        Hero.instance.x += speedX;
        Hero.instance.y += speedY;
        if (Hero.instance.x > (fieldSize - 5) * 40) Hero.instance.x = (fieldSize - 5) * 40;
        if (Hero.instance.x < 0) {
            Hero.instance.x = 0;
            Hero.instance.takeDamage(1, WeaponType.Ordinary);
        }
        if (Hero.instance.y > (fieldSize - 5) * 40) Hero.instance.y = (fieldSize - 5) * 40;
        if (Hero.instance.y < 0) Hero.instance.y = 0;
        currentFrame = setFrame();
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
