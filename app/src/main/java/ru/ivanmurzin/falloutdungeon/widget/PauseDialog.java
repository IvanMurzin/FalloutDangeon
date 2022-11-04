package ru.ivanmurzin.falloutdungeon.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.SpecialType;

public class PauseDialog extends Dialog {

    public PauseDialog(Activity activity) {
        super(activity);
    }

    private String getSpecialText(SpecialType type) {
        int value = Hero.instance.special.getSpecial(type).getValue();
        if (value < 3) return "Плохо  ";
        if (value < 5) return "Средне ";
        if (value < 7) return "Хорошо ";
        if (value < 9) return "Оч.хор.";
        return "Бог    ";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.menu_dialog);
        initSpecial();
        initExperience();
        initButtons();
    }


    @SuppressLint("DefaultLocale")
    private void initSpecial() {
        TextView strength = findViewById(R.id.strength);
        TextView strength_text = findViewById(R.id.strength_text);
        TextView perception = findViewById(R.id.perception);
        TextView perception_text = findViewById(R.id.perception_text);
        TextView endurance = findViewById(R.id.endurance);
        TextView endurance_text = findViewById(R.id.endurance_text);
        TextView charisma = findViewById(R.id.charisma);
        TextView charisma_text = findViewById(R.id.charisma_text);
        TextView intelligence = findViewById(R.id.intelligence);
        TextView intelligence_text = findViewById(R.id.intelligence_text);
        TextView agility = findViewById(R.id.agility);
        TextView agility_text = findViewById(R.id.agility_text);
        TextView luck = findViewById(R.id.luck);
        TextView luckText = findViewById(R.id.luck_text);

        strength.setText(String.format("%02d", Hero.instance.special.getSpecial(SpecialType.Strength).getValue()));
        strength_text.setText(getSpecialText(SpecialType.Strength));
        perception.setText(String.format("%02d", Hero.instance.special.getSpecial(SpecialType.Perception).getValue()));
        perception_text.setText(getSpecialText(SpecialType.Perception));

        endurance.setText(String.format("%02d", Hero.instance.special.getSpecial(SpecialType.Endurance).getValue()));
        endurance_text.setText(getSpecialText(SpecialType.Endurance));

        charisma.setText(String.format("%02d", Hero.instance.special.getSpecial(SpecialType.Charisma).getValue()));
        charisma_text.setText(getSpecialText(SpecialType.Charisma));

        intelligence.setText(String.format("%02d", Hero.instance.special.getSpecial(SpecialType.Intelligence).getValue()));
        intelligence_text.setText(getSpecialText(SpecialType.Intelligence));

        agility.setText(String.format("%02d", Hero.instance.special.getSpecial(SpecialType.Agility).getValue()));
        agility_text.setText(getSpecialText(SpecialType.Agility));

        luck.setText(String.format("%02d", Hero.instance.special.getSpecial(SpecialType.Luck).getValue()));
        luckText.setText(getSpecialText(SpecialType.Luck));
    }

    @SuppressLint("DefaultLocale")
    private void initExperience() {
        TextView experience = findViewById(R.id.experience);
        TextView experience_required = findViewById(R.id.experience_required);
        TextView level = findViewById(R.id.level);
        experience.setText(String.valueOf(Hero.instance.experience.getCurrent()));
        experience_required.setText(String.valueOf(Hero.instance.experience.getRequired()));
        level.setText(String.format("%02d", Hero.instance.experience.getLevel()));
    }

    private void initButtons() {
        ImageView exit = findViewById(R.id.exit_button);
        ImageView resume = findViewById(R.id.resume_button);
        exit.setOnClickListener(view -> {
            dismiss();
        });
        resume.setOnClickListener(view -> {
            dismiss();
        });
    }
}
