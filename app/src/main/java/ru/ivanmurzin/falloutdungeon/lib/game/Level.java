package ru.ivanmurzin.falloutdungeon.lib.game;

import java.util.HashSet;
import java.util.Set;

import ru.ivanmurzin.falloutdungeon.lib.InteractiveGameObject;
import ru.ivanmurzin.falloutdungeon.lib.game.object.Cell;
import ru.ivanmurzin.falloutdungeon.lib.game.object.GameItem;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.Chest;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.lib.unit.Unit;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;

public class Level {
    private final static int HIT_RADIUS = 60;
    public final int levelNumber;
    public final int fieldSize;
    public final Cell[][] cells;
    private final Set<InteractiveGameObject> interactiveGameObjects;
    private final Set<Unit> units;
    private final Set<Weapon.Bullet> bullets;


    public Level(int levelNumber, int fieldSize) {
        cells = new Cell[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                cells[i][j] = new Cell(i * 40, j * 40);
            }
        }
        this.levelNumber = levelNumber;
        this.fieldSize = fieldSize;
        interactiveGameObjects = new HashSet<>();
        units = new HashSet<>();
        units.add(Hero.instance);
        bullets = new HashSet<>();
    }


    public Set<InteractiveGameObject> getInteractiveGameObjects() {
        return interactiveGameObjects;
    }

    public Set<Weapon.Bullet> getBullets() {
        return bullets;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public void addChest(Chest chests) {
        interactiveGameObjects.add(chests);
    }

    public void addDroppedItem(Item item, float x, float y) {
        GameItem gameItem = new GameItem(item, this, x, y);
        interactiveGameObjects.add(gameItem);
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void addBullet(Weapon.Bullet bullet) {
        bullets.add(bullet);
    }

    public void removeInteractive(InteractiveGameObject object) {
        interactiveGameObjects.remove(object);
    }

    public void update() {
        for (Weapon.Bullet bullet : bullets) {
            bullet.onMove();
        }
        for (Unit unit : units) {
            unit.onMove();
            if (unit.x > (fieldSize - 5) * 40 || unit.x < 0 || unit.y > (fieldSize - 5) * 40 || unit.y < 0) {
                if (unit.x > (fieldSize - 5) * 40) unit.x = (fieldSize - 5) * 40;
                else if (unit.x < 0) unit.x = 0;
                else if (unit.y > (fieldSize - 5) * 40) unit.y = (fieldSize - 5) * 40;
                else unit.y = 0;
            }
            for (Weapon.Bullet bullet : bullets) {
                if (unit.getDistance(bullet.x, bullet.y) < HIT_RADIUS) {
                    if (unit.onShoot(bullet)) {
                        bullets.remove(bullet);
                        if (unit.getHealth() == 0) {
                            unit.onDie();
                            units.remove(unit);
                            break;
                        }
                    }
                }
            }
        }
    }
}
