package ru.ivanmurzin.falloutdungeon.controller;

import android.content.Context;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

public class NotifyController implements Loggable {
    private final Context context;

    public NotifyController(Context context) {
        this.context = context;
    }

    private void showToast(String message, int type) {
        FancyToast.makeText(context, message, Toast.LENGTH_SHORT, type, false).show();
    }

    @Override
    public void notifyError(String message) {
        showToast(message, FancyToast.ERROR);
    }

    @Override
    public void notifyInfo(String message) {
        showToast(message, FancyToast.INFO);
    }

    @Override
    public void notify(String message) {
        showToast(message, FancyToast.DEFAULT);
    }
}
