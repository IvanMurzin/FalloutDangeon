package ru.ivanmurzin.falloutdungeon.controller.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.controller.ui.JoystickController;

public class JoystickAdapter {
    public final JoystickController controller;
    private final Bitmap buttonIn;
    private final Bitmap buttonOut;

    public JoystickAdapter(Context context, float centerX, float centerY) {
        buttonIn = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.button_in), 100, 100, false);
        buttonOut = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.button_out), 150 + buttonIn.getWidth() / 2, 150 + buttonIn.getHeight() / 2, false);
        this.controller = new JoystickController(centerX, centerY);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(buttonOut, controller.getCenterX() - buttonOut.getWidth() / 2f, controller.getCenterY() - buttonOut.getHeight() / 2f, null);
        canvas.drawBitmap(buttonIn, controller.getPositionX() - buttonIn.getWidth() / 2f, controller.getPositionY() - buttonIn.getHeight() / 2f, null);
    }
}
