package ru.ivanmurzin.falloutdungeon.lib.game;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ru.ivanmurzin.falloutdungeon.lib.GameObject;
import ru.ivanmurzin.falloutdungeon.lib.InteractiveGameObject;
import ru.ivanmurzin.falloutdungeon.lib.game.object.Cell;
import ru.ivanmurzin.falloutdungeon.lib.game.object.GameItem;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.Chest;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.lib.unit.Unit;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;

public class Level {
    public final static int TILE_SIZE = 40;
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
                cells[i][j] = new Cell(i * TILE_SIZE, j * TILE_SIZE);
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

    private boolean isOutOfBorders(GameObject object) {
        return object.x > fieldSize * TILE_SIZE || object.x < 0 || object.y > fieldSize * TILE_SIZE || object.y < 0;
    }

    public void update() {
        Iterator<Weapon.Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Weapon.Bullet bullet = bulletIterator.next();
            bullet.onMove();
            if (isOutOfBorders(bullet)) bulletIterator.remove();
        }
        Iterator<Unit> unitIterator = units.iterator();
        while (unitIterator.hasNext()) {
            Unit unit = unitIterator.next();
            unit.onMove();
            if (isOutOfBorders(unit)) {
                if (unit.x > (fieldSize - 5) * TILE_SIZE) unit.x = (fieldSize - 5) * TILE_SIZE;
                else if (unit.x < 0) unit.x = 0;
                else if (unit.y > (fieldSize - 5) * TILE_SIZE) unit.y = (fieldSize - 5) * TILE_SIZE;
                else unit.y = 0;
            }
            bulletIterator = bullets.iterator();
            while (bulletIterator.hasNext()) {
                Weapon.Bullet bullet = bulletIterator.next();
                if (unit.getDistance(bullet.x, bullet.y) < HIT_RADIUS) {
                    if (unit.onShoot(bullet)) {
                        bulletIterator.remove();
                        if (unit.getHealth() == 0) {
                            unit.onDie();
                            unitIterator.remove();
                            break;
                        }
                    }
                }
            }
        }
    }
}
