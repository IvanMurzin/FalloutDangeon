package ru.ivanmurzin.falloutdungeon.lib.game;

import android.content.Context;

import java.util.HashSet;
import java.util.Set;

import ru.ivanmurzin.falloutdungeon.lib.GameObject;
import ru.ivanmurzin.falloutdungeon.lib.InteractiveGameObject;
import ru.ivanmurzin.falloutdungeon.lib.MovingGameObject;
import ru.ivanmurzin.falloutdungeon.lib.game.object.Cell;
import ru.ivanmurzin.falloutdungeon.lib.game.object.GameItem;
import ru.ivanmurzin.falloutdungeon.lib.game.object.chest.Chest;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;

public class Level {
    public final int levelNumber;
    public final int fieldSize;
    public final Cell[][] cells;
    private final Set<InteractiveGameObject> interactiveGameObjects;
    private final Set<MovingGameObject> movingGameObjects;
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
        movingGameObjects = new HashSet<>();
        objectsToRemove = new HashSet<>();
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

    public void addMovingObject(MovingGameObject object) {
        objects.add(object);
        movingGameObjects.add(object);
    }

    public void removeInteractive(InteractiveGameObject object) {
        objects.remove(object);
        interactiveGameObjects.remove(object);
    }

    public void removeMoving(MovingGameObject object) {
        objects.remove(object);
        movingGameObjects.remove(object);
    }

    public Level getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(Level nextLevel) {
        this.nextLevel = nextLevel;
    }

    public void update() {
        for (MovingGameObject object : movingGameObjects) {
            object.move();
            if (object.x > (fieldSize - 5) * 40 || object.x < 0 || object.y > (fieldSize - 5) * 40 || object.y < 0) {
                if (object.removeOnCollapse) objectsToRemove.add(object);
                else {
                    if (object.x > (fieldSize - 5) * 40) object.x = (fieldSize - 5) * 40;
                    else if (object.x < 0) object.x = 0;
                    else if (object.y > (fieldSize - 5) * 40) object.y = (fieldSize - 5) * 40;
                    else object.y = 0;
                }
            }
        }
        objects.removeAll(objectsToRemove);
        movingGameObjects.removeAll(objectsToRemove);
        objectsToRemove.clear();
    }

    public Level getPreviousLevel() {
        return previousLevel;
    }
}
