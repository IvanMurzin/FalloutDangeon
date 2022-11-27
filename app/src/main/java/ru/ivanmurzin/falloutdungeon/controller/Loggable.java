package ru.ivanmurzin.falloutdungeon.controller;

public interface Loggable {
    void notifyError(String message);

    void notifyInfo(String message);

    void notify(String message);
}
