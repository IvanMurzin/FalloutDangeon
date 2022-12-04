package ru.ivanmurzin.falloutdungeon.lib.game;

import android.content.Context;

import java.util.HashSet;
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
    private final static int hitRadius = 60;
    public final int levelNumber;
    public final int fieldSize;
    public final Cell[][] cells;
    private final Set<InteractiveGameObject> interactiveGameObjects;
    private final Set<Unit> units;
    private final Set<Weapon.Bullet> bullets;
    private final Set<GameObject> objects;
    private final Set<GameObject> objectsToRemove;
    private Level nextLevel;
    private Level previousLevel;

    public Level(Context context, int levelNumber, int fieldSize) {
        cells = new Cell[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                cells[i][j] = new Cell(i * 40, j * 40);
            }
        }
        this.levelNumber = levelNumber;
        this.fieldSize = fieldSize;
        objects = new HashSet<>();
        interactiveGameObjects = new HashSet<>();
        units = new HashSet<>();
        units.add(Hero.instance);
        objectsToRemove = new HashSet<>();
        bullets = new HashSet<>();
    }

    public Set<GameObject> getObjects() {
        return objects;
    }

    public Set<InteractiveGameObject> getInteractiveGameObjects() {
        return interactiveGameObjects;
    }

    public void addChests(Set<Chest> chests) {
        interactiveGameObjects.addAll(chests);
        objects.addAll(chests);
    }

    public void addDroppedItem(Item item, float x, float y) {
        GameItem gameItem = new GameItem(item, this, x, y);
        objects.add(gameItem);
        interactiveGameObjects.add(gameItem);
    }

    public void addUnit(Unit unit) {
        objects.add(unit);
        units.add(unit);
    }

    public void addBullet(Weapon.Bullet bullet) {
        objects.add(bullet);
        bullets.add(bullet);
    }

    public void removeInteractive(InteractiveGameObject object) {
        objects.remove(object);
        interactiveGameObjects.remove(object);
    }

    public void removeUnit(Unit unit) {
        objects.remove(unit);
        units.remove(unit);
    }

    public Level getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(Level nextLevel) {
        this.nextLevel = nextLevel;
    }

    public void update() {
        for (Unit unit : units) {
            unit.onMove();
            if (unit.x > (fieldSize - 5) * 40 || unit.x < 0 || unit.y > (fieldSize - 5) * 40 || unit.y < 0) {
                if (unit.x > (fieldSize - 5) * 40) unit.x = (fieldSize - 5) * 40;
                else if (unit.x < 0) unit.x = 0;
                else if (unit.y > (fieldSize - 5) * 40) unit.y = (fieldSize - 5) * 40;
                else unit.y = 0;
            }
            for (Weapon.Bullet bullet : bullets) {
                bullet.onMove();
                if (unit.getDistance(bullet.x, bullet.y) < hitRadius) {
                    if (unit.onShoot(bullet)) {
                        objectsToRemove.add(bullet);
                        if (unit.getHealth() == 0) {
                            unit.onDie();
                            objectsToRemove.add(unit);
                            break;
                        }
                    }
                }
            }
            objects.removeAll(objectsToRemove);
            units.removeAll(objectsToRemove);
            objectsToRemove.clear();
        }
    }

    public Level getPreviousLevel() {
        return previousLevel;
    }
}
