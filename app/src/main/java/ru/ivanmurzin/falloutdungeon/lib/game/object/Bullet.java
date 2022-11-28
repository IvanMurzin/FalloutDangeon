package ru.ivanmurzin.falloutdungeon.lib.game.object;

import static java.lang.Math.abs;

import ru.ivanmurzin.falloutdungeon.lib.MovingGameObject;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;

public class Bullet extends MovingGameObject {
    public final Direction direction;
    public final boolean fromHero;

    public Bullet(float speedX, float speedY) {
        this(Hero.instance.x + 60, Hero.instance.y + 75, speedX, speedY);
    }

    public Bullet(float speedX, float speedY, boolean fromHero) {
        this(Hero.instance.x + 60, Hero.instance.y + 75, speedX, speedY, fromHero);
    }

    public Bullet(float x, float y, float speedX, float speedY) {
        this(x, y, speedX, speedY, false);
    }

    public Bullet(float x, float y, float speedX, float speedY, boolean fromHero) {
        super(x, y, 100, true);
        this.fromHero = fromHero;
        if (speedX > 0 && abs(speedX) > abs(speedY)) {
            direction = Direction.East;
            return;
        }
        if (speedX < 0 && abs(speedX) > abs(speedY)) {
            direction = Direction.West;
            return;
        }
        if (speedY > 0) {
            direction = Direction.South;
            return;
        }
        direction = Direction.North;
    }

    @Override
    public void move() {
        switch (direction) {
            case South:
                y += speed;
                break;
            case North:
                y -= speed;
                break;
            case West:
                x -= speed;
                break;
            case East:
                x += speed;
                break;
        }
    }

    public enum Direction {
        South,
        North,
        West,
        East,
    }
}
