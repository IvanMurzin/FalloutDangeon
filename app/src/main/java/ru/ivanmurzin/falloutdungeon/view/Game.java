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

import ru.ivanmurzin.falloutdungeon.lib.game.Level;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holder;
    private GameThread gameThread;
    private static final int fps = 30;
    private Level level;
    private Player player;
    private GameDisplay gameDisplay;


    public Game(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder = holder;
        player = new Player(getContext(), getWidth(), getHeight());
        level = new Level(getContext(), player.hero, 1, 200);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);
        gameThread = new GameThread();
        gameThread.start();
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

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        gameThread.running = false;
    }

    public void update() {
        gameDisplay.update();
        player.update();
    }

    public void drawFrames(Canvas canvas, GameDisplay display) {
        level.draw(canvas, display);
        player.draw(canvas, display);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (player.joystick.isInJoystick(event.getX(event.getActionIndex()), (event.getY(event.getActionIndex())))) {
                    player.joystick.setJoystickPointerId(event.getPointerId(event.getActionIndex()));
                    player.joystick.setPressed(true);
                    player.joystick.setActuator(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()));
                }
                player.joystick.incTouchCount();
                return true;
            case MotionEvent.ACTION_MOVE:
                if (player.joystick.isPressed()) {
                    float x = event.getX();
                    float y = event.getY();
                    if (player.joystick.getTouchCount() > 1) {
                        int i1 = event.findPointerIndex(event.getPointerId(0));
                        int i2 = event.findPointerIndex(event.getPointerId(1));
                        x = event.getX(i1);
                        y = event.getY(i1);
                        if (x > getWidth() / 2f) {
                            x = event.getX(i2);
                            y = event.getY(i2);
                        }
                    }
                    player.joystick.setActuator(x, y);
                }

                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (player.joystick.getJoystickPointerId() == event.getPointerId(event.getActionIndex())) {
                    player.joystick.setPressed(false);
                    player.joystick.resetActuator();
                }
                player.joystick.decTouchCount();
                return true;
        }
        return true;
    }
}