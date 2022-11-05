package ru.ivanmurzin.falloutdungeon.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import ru.ivanmurzin.falloutdungeon.controller.object.LevelController;
import ru.ivanmurzin.falloutdungeon.controller.object.unit.HeroController;
import ru.ivanmurzin.falloutdungeon.controller.ui.JoystickController;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private static final int fps = 30;
    private SurfaceHolder holder;
    private GameThread gameThread;
    private GameDisplay gameDisplay;
    private LevelController levelController;
    private HeroController heroController;


    public Game(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder = holder;
        heroController = new HeroController(getContext(), getWidth(), getHeight(), 40);
        levelController = new LevelController(getContext());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, heroController);
        gameThread = new GameThread();
        gameThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        gameThread.running = false;
    }

    public void update() {
        gameDisplay.update();
        heroController.update();
    }

    public void drawFrames(Canvas canvas, GameDisplay display) {
        levelController.draw(canvas, display);
        heroController.draw(canvas, display);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        JoystickController joystick = heroController.joystickController;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (joystick.isInJoystick(event.getX(event.getActionIndex()), (event.getY(event.getActionIndex())))) {
                    joystick.setJoystickPointerId(event.getPointerId(event.getActionIndex()));
                    joystick.setPressed(true);
                    joystick.setActuator(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()));
                }
                joystick.incTouchCount();
                return true;
            case MotionEvent.ACTION_MOVE:
                if (joystick.isPressed()) {
                    float x = event.getX();
                    float y = event.getY();
                    if (joystick.getTouchCount() > 1) {
                        int i1 = event.findPointerIndex(event.getPointerId(0));
                        int i2 = event.findPointerIndex(event.getPointerId(1));
                        x = event.getX(i1);
                        y = event.getY(i1);
                        if (x > getWidth() / 2f) {
                            x = event.getX(i2);
                            y = event.getY(i2);
                        }
                    }
                    joystick.setActuator(x, y);
                }

                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (joystick.getJoystickPointerId() == event.getPointerId(event.getActionIndex())) {
                    joystick.setPressed(false);
                    joystick.resetActuator();
                }
                joystick.decTouchCount();
                return true;
        }
        return true;
    }

    private class GameThread extends Thread {
        private volatile boolean running = true;

        @SuppressWarnings("BusyWait")
        @Override
        public void run() {
            while (running) {
                Canvas canvas = holder.lockCanvas();
                try {
                    sleep(1000 / fps);
                    drawFrames(canvas, gameDisplay);
                    update();
                } catch (Exception ignored) {
                } finally {
                    try {
                        holder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}