package ru.ivanmurzin.falloutdungeon.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.databinding.MenuDialogBinding;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.SpecialType;

public class PauseDialog extends Dialog {

    private MenuDialogBinding binding;

    public PauseDialog(Activity activity) {
        super(activity);
    }

    private int getWeaponId() {
        switch (Hero.instance.getWeapon().getType()) {
            case Ordinary:
                return R.drawable.pistol;
            case Laser:
                return R.drawable.laser_pistol;
            default:
                return R.drawable.cryolator;
        }
    }

    private int getBulletId() {
        switch (Hero.instance.getWeapon().getType()) {
            case Ordinary:
                return R.drawable.bullet_right;
            case Laser:
                return R.drawable.laser_bullet_horizontal;
            default:
                return R.drawable.frost_bullet_right;
        }
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
        binding = MenuDialogBinding.inflate(getLayoutInflater());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(binding.getRoot());
        initSpecial();
        initExperience();
        initButtons();
        initData();
    }


    private void initData() {
        binding.lockpicks.setText(String.valueOf(Hero.instance.getLockpicks()));
        binding.weaponIcon.setImageResource(getWeaponId());
        binding.weaponBullet.setImageResource(getBulletId());
        binding.weaponDamage.setText(String.valueOf(Hero.instance.getWeapon().getMaxDamage()));
        binding.weaponReload.setText(String.valueOf(Hero.instance.getWeapon().getReload()));
    }

    @SuppressLint("DefaultLocale")
    private void initSpecial() {
        binding.strength.setText(String.format("%02d", Hero.instance.special.getSpecial(SpecialType.Strength).getValue()));
        binding.strengthText.setText(getSpecialText(SpecialType.Strength));
        binding.perception.setText(String.format("%02d", Hero.instance.special.getSpecial(SpecialType.Perception).getValue()));
        binding.perceptionText.setText(getSpecialText(SpecialType.Perception));

        binding.endurance.setText(String.format("%02d", Hero.instance.special.getSpecial(SpecialType.Endurance).getValue()));
        binding.enduranceText.setText(getSpecialText(SpecialType.Endurance));

        binding.charisma.setText(String.format("%02d", Hero.instance.special.getSpecial(SpecialType.Charisma).getValue()));
        binding.charismaText.setText(getSpecialText(SpecialType.Charisma));

        binding.intelligence.setText(String.format("%02d", Hero.instance.special.getSpecial(SpecialType.Intelligence).getValue()));
        binding.intelligenceText.setText(getSpecialText(SpecialType.Intelligence));

        binding.agility.setText(String.format("%02d", Hero.instance.special.getSpecial(SpecialType.Agility).getValue()));
        binding.agilityText.setText(getSpecialText(SpecialType.Agility));

        binding.luck.setText(String.format("%02d", Hero.instance.special.getSpecial(SpecialType.Luck).getValue()));
        binding.luckText.setText(getSpecialText(SpecialType.Luck));
    }

    @SuppressLint("DefaultLocale")
    private void initExperience() {
        binding.experience.setText(String.valueOf(Hero.instance.experience.getCurrent()));
        binding.experienceRequired.setText(String.valueOf(Hero.instance.experience.getRequired()));
        binding.level.setText(String.format("%02d", Hero.instance.experience.getLevel()));
    }

    private void initButtons() {
        binding.exitButton.setOnClickListener(view -> {
            dismiss();
        });
        binding.resumeButton.setOnClickListener(view -> {
            dismiss();
        });
    }
}
