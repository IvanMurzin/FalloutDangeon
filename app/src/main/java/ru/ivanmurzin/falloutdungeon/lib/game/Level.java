package ru.ivanmurzin.falloutdungeon.lib.game;

import android.content.Context;

import java.util.HashSet;
import java.util.Set;

import ru.ivanmurzin.falloutdungeon.lib.GameObject;
import ru.ivanmurzin.falloutdungeon.lib.game.object.Bullet;
import ru.ivanmurzin.falloutdungeon.lib.game.object.Cell;
import ru.ivanmurzin.falloutdungeon.lib.game.object.GameItem;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.Chest;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;

public class Level {
    public final int levelNumber;
    public final int fieldSize;
    public final Cell[][] cells;
    private final Set<InteractiveGameObject> interactiveGameObjects;
    private final Set<GameObject> objects;
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

    public void removeInteractive(InteractiveGameObject object) {
        objects.remove(object);
        interactiveGameObjects.remove(object);
    }

    public void addBullet(Bullet bullet) {
        interactiveGameObjects.add(bullet);
    }

    public Level getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(Level nextLevel) {
        this.nextLevel = nextLevel;
    }

    public Level getPreviousLevel() {
        return previousLevel;
    }
}
