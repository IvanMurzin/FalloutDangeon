package ru.ivanmurzin.falloutdungeon.controller;

public interface Logger {
    void notifyError(String message);

    void notifyInfo(String message);

    void notifySuccess(String message);

    void notifyWarning(String message);

    void notify(String message);
}
