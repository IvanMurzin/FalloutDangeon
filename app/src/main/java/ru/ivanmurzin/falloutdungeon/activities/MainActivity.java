package ru.ivanmurzin.falloutdungeon.activities;

import android.os.Bundle;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.view.Game;
import ru.ivanmurzin.falloutdungeon.widget.PauseDialog;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        RelativeLayout.LayoutParams fillParentLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout rootPanel = new RelativeLayout(this);
        rootPanel.setLayoutParams(fillParentLayout);

        initGame(rootPanel);
        initMenuButton(rootPanel);
        setContentView(rootPanel);
    }

    private void initGame(ViewGroup rootPanel) {
        SurfaceView game = new Game(this);
        game.setZOrderMediaOverlay(false);
        rootPanel.addView(game);
    }

    private void initMenuButton(ViewGroup rootPanel) {
        ImageView menu = new ImageView(this);
        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        buttonParams.width = 100;
        buttonParams.height = 100;
        buttonParams.topMargin = 60;
        buttonParams.rightMargin = 60;
        menu.setLayoutParams(buttonParams);
        menu.setPadding(0, 0, 0, 0);
        menu.setImageResource(R.drawable.pause);
        menu.setOnClickListener(view -> {
            PauseDialog pauseDialog = new PauseDialog(this);
            pauseDialog.show();
        });
        rootPanel.addView(menu);
    }
}