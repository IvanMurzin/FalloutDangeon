package ru.ivanmurzin.falloutdungeon.view;


import ru.ivanmurzin.falloutdungeon.controller.object.unit.UnitController;

public class GameDisplay {
    private final UnitController centerController;
    private final double displayCenterX;
    private final double displayCenterY;
    private double offsetX;
    private double offsetY;

    public GameDisplay(double widthPixels, double heightPixels, UnitController centerController) {
        this.centerController = centerController;
        displayCenterX = widthPixels / 2.0;
        displayCenterY = heightPixels / 2.0;
        update();
    }

    public void update() {
        offsetX = displayCenterX - centerController.getX();
        offsetY = displayCenterY - centerController.getY();
    }

    public float offsetX(double x) {
        return (float) (x + offsetX);
    }

    public float offsetY(double y) {
        return (float) (y + offsetY);
    }
}