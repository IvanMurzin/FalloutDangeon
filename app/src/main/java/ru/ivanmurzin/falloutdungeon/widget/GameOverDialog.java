package ru.ivanmurzin.falloutdungeon.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import ru.ivanmurzin.falloutdungeon.databinding.GameOverDialogBinding;

public class GameOverDialog extends Dialog {

    private GameOverDialogBinding binding;

    public GameOverDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = GameOverDialogBinding.inflate(getLayoutInflater());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(binding.getRoot());

        binding.restart.setOnClickListener(view->{
            dismiss();
        });
    }
}
