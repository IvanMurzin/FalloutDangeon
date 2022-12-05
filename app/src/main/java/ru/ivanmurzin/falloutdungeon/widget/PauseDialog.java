package ru.ivanmurzin.falloutdungeon.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import ru.ivanmurzin.falloutdungeon.R;
import ru.ivanmurzin.falloutdungeon.databinding.MenuDialogBinding;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.Armor;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.LeatherBreastplate;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.LeatherHelmet;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.MetalBreastplate;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.MetalHelmet;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.LaserPistol;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Pistol;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.SpecialType;
import ru.ivanmurzin.falloutdungeon.view.adapter.AidAdapter;

public class PauseDialog extends Dialog {

    private MenuDialogBinding binding;

    public PauseDialog(Activity activity) {
        super(activity);
    }

    private int getWeaponId() {
        Weapon weapon = Hero.instance.getWeapon();
        if (weapon instanceof Pistol) return R.drawable.pistol;
        if (weapon instanceof LaserPistol) return R.drawable.laser_pistol;
        return R.drawable.cryolator;
    }

    private int getBulletId(WeaponType type) {
        switch (type) {
            case Ordinary:
                return R.drawable.bullet_right;
            case Laser:
                return R.drawable.laser_bullet_horizontal;
            default:
                return R.drawable.frost_bullet_right;
        }
    }

    @Nullable
    private Integer getBreastplateId() {
        Armor breastplate = Hero.instance.getBreastplate();
        if (breastplate instanceof LeatherBreastplate) return R.drawable.leather_breatplate;
        if (breastplate instanceof MetalBreastplate) return R.drawable.metal_breastplate;
        return null;
    }

    @Nullable
    private Integer getHelmetId() {
        Armor helmet = Hero.instance.getHelmet();
        if (helmet instanceof LeatherHelmet) return R.drawable.leather_helmet;
        if (helmet instanceof MetalHelmet) return R.drawable.metal_helmet;
        return null;
    }

    private int getBreastplateResistanceBulletId() {
        return getBulletId(Hero.instance.getBreastplate().getResistanceType());
    }

    private int getHelmetResistanceBulletId() {
        return getBulletId(Hero.instance.getHelmet().getResistanceType());
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
        initRecycler();
    }

    private void initRecycler() {
        binding.aidRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.aidRecycler.setAdapter(new AidAdapter(Hero.instance.getAids()));
    }


    private void initData() {
        binding.lockpicks.setText(String.valueOf(Hero.instance.getLockpicks()));
        binding.weaponIcon.setImageResource(getWeaponId());
        binding.weaponBullet.setImageResource(getBulletId(Hero.instance.getWeapon().getType()));
        binding.weaponDamage.setText(String.valueOf(Hero.instance.getWeapon().getMaxDamage()));
        binding.weaponReload.setText(String.valueOf(Hero.instance.getWeapon().getReload()));

        Integer helmetId = getHelmetId();
        if (helmetId != null) {
            binding.helmetIcon.setImageResource(helmetId);
            binding.helmetArmor.setText(String.valueOf(Hero.instance.getHelmet().getDefaultArmor()));
            binding.helmetResistanceIcon.setImageResource(getHelmetResistanceBulletId());
        }
        Integer breastplateId = getBreastplateId();
        if (breastplateId != null) {
            binding.breastplateIcon.setImageResource(breastplateId);
            binding.breastplateArmor.setText(String.valueOf(Hero.instance.getBreastplate().getDefaultArmor()));
            binding.breastplateResistanceIcon.setImageResource(getBreastplateResistanceBulletId());
        }
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
