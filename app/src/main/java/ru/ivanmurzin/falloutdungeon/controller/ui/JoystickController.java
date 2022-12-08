package ru.ivanmurzin.falloutdungeon.controller.ui;

public class JoystickController {
    private final float centerX;
    private final float centerY;
    private final float outRadius;
    private float positionX;
    private float positionY;
    private float actuatorX;
    private float actuatorY;
    private boolean isPressed = false;
    private int joystickPointerId = 0;
    private int touchCount = 0;

    public JoystickController(float centerX, float centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.outRadius = 150;
        this.positionX = centerX;
        this.positionY = centerY;
    }

    public int getJoystickPointerId() {
        return joystickPointerId;
    }

    public void setJoystickPointerId(int joystickPointerId) {
        this.joystickPointerId = joystickPointerId;
    }

    public int getTouchCount() {
        return touchCount;
    }

    public void incTouchCount() {
        this.touchCount++;
    }

    public void decTouchCount() {
        this.touchCount--;
    }


    public void update() {
        positionX = centerX + actuatorX * outRadius;
        positionY = centerY + actuatorY * outRadius;
    }

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public boolean isInJoystick(float x, float y) {
        float distance = (float) Math.sqrt(Math.pow((centerX - x), 2) + Math.pow((y - centerY), 2));
        return distance <= outRadius;
    }

    public void setActuator(float actuatorX, float actuatorY) {
        float deltaX = actuatorX - centerX;
        float deltaY = actuatorY - centerY;
        float delta = (float) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        if (delta < outRadius) {
            this.actuatorX = deltaX / outRadius;
            this.actuatorY = deltaY / outRadius;
        } else {
            this.actuatorX = deltaX / delta;
            this.actuatorY = deltaY / delta;
        }
    }

    public float getActuatorX() {
        return actuatorX;
    }

    public float getActuatorY() {
        return actuatorY;
    }


    public void resetActuator() {
        actuatorX = 0;
        actuatorY = 0;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }
}
