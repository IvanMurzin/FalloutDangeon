package ru.ivanmurzin.falloutdungeon.lib.unit.enemy;

import ru.ivanmurzin.falloutdungeon.lib.game.Level;
import ru.ivanmurzin.falloutdungeon.lib.game.object.Bullet;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Pistol;
import ru.ivanmurzin.falloutdungeon.lib.item.lockpick.Lockpick;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;

public class Raider extends Enemy {
    private final Level level;
    private int reload = 0;

    public Raider(float x, float y, Level level) {
        super(x, y, 7, 100, 10, new Pistol(), 100, new Lockpick(3), null);
        this.level = level;
    }

    @Override
    public void move() {
        double distance = Hero.instance.getDistance(x + 50, y + 40);
        if (distance < 600 && distance > 150) {
            float dx = Hero.instance.x - x;
            float dy = Hero.instance.y - y;
            if (reload == 0) {
                level.addMovingObject(new Bullet(x + 50, y + 40, dx, dy));
            }
            reload = (reload + 1) % 30;
            if (dx > 0) x += speed;
            else x -= speed;
            if (dy > 0) y += speed;
            else y -= speed;
        }
    }

    @Override
    public void getShoot(Bullet bullet) {
        if (!bullet.fromHero) return;
        health -= 10;
    }
}
