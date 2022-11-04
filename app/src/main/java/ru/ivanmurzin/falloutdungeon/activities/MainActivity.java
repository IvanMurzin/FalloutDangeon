package ru.ivanmurzin.falloutdungeon.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ru.ivanmurzin.falloutdungeon.view.Game;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Game(this));
    }
}