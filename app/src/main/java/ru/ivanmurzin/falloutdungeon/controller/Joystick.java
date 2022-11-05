package ru.ivanmurzin.falloutdungeon.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import ru.ivanmurzin.falloutdungeon.R;

public class Joystick {
    private final float centerX;
    private final float centerY;
    private final float outRadius;
    private final Bitmap buttonIn;
    private final Bitmap buttonOut;
    private float positionX;
    private float positionY;
    private float actuatorX;
    private float actuatorY;
    private boolean isPressed = false;
    private int joystickPointerId = 0;
    private int touchCount = 0;

    public Joystick(Context context, float centerX, float centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.outRadius = 150;
        this.positionX = centerX;
        this.positionY = centerY;
        buttonIn = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.button_in), 100, 100, false);
        buttonOut = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.button_out), 150 + buttonIn.getWidth() / 2, 150 + buttonIn.getHeight() / 2, false);
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

    public void draw(Canvas canvas) {
        canvas.drawBitmap(buttonOut, centerX - buttonOut.getWidth() / 2f, centerY - buttonOut.getHeight() / 2f, null);
        canvas.drawBitmap(buttonIn, positionX - buttonIn.getWidth() / 2f, positionY - buttonIn.getHeight() / 2f, null);
    }

    public void update() {
        positionX = centerX + actuatorX * outRadius;
        positionY = centerY + actuatorY * outRadius;
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
