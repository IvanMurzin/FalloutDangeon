package ru.ivanmurzin.falloutdungeon.view;


import ru.ivanmurzin.falloutdungeon.lib.GameObject;

public class GameDisplay {
    private final GameObject centerObject;
    private final double displayCenterX;
    private final double displayCenterY;
    private double offsetX;
    private double offsetY;

    public GameDisplay(double widthPixels, double heightPixels, GameObject centerObject) {
        this.centerObject = centerObject;
        displayCenterX = widthPixels / 2.0;
        displayCenterY = heightPixels / 2.0;
        update();
    }

    public void update() {
        offsetX = displayCenterX - centerObject.x;
        offsetY = displayCenterY - centerObject.y;
    }

    public float offsetX(double x) {
        return (float) (x + offsetX);
    }

    public float offsetY(double y) {
        return (float) (y + offsetY);
    }
}