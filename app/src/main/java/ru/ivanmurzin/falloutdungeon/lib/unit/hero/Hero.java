package ru.ivanmurzin.falloutdungeon.lib.unit.hero;

import static java.lang.Math.sqrt;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import ru.ivanmurzin.falloutdungeon.Constants;
import ru.ivanmurzin.falloutdungeon.lib.item.aid.Aid;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.Armor;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.ArmorType;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Cryolator;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;
import ru.ivanmurzin.falloutdungeon.lib.unit.Unit;

public class Hero extends Unit {
    private static Hero instance = new Hero();
    public final Special special;
    public final Experience experience;
    private final List<Aid> aids;
    private Weapon weapon;
    private Armor helmet;
    private Armor breastplate;
    private int lockpicks;

    private Hero() {
        super(100, 10);
        special = new Special();
        special.setSpecial(SpecialType.Strength, 2);
        special.setSpecial(SpecialType.Perception, 4);
        special.setSpecial(SpecialType.Endurance, 5);
        special.setSpecial(SpecialType.Charisma, 6);
        special.setSpecial(SpecialType.Intelligence, 7);
        special.setSpecial(SpecialType.Agility, 8);
        special.setSpecial(SpecialType.Luck, 10);
        lockpicks = 15;
        experience = new Experience();
        weapon = new Cryolator();
        aids = new LinkedList<>();
    }

    public void switchWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void switchArmor(Armor armor) {
        if (armor.type == ArmorType.Breastplate) breastplate = armor;
        else helmet = armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void addLockpicks(int lockpicks) {
        this.lockpicks += lockpicks;
    }

    public boolean addAid(Aid aid) {
        int aidWeight = 0;
        for (Aid myAid : aids) {
            aidWeight += myAid.weight;
        }
        if (aidWeight >= special.getMaxWeight()) return false;
        aids.add(aid);
        return true;
    }

    public int getLockpicks() {
        return lockpicks;
    }

    public boolean decreaseLockpick() {
        if (lockpicks == 0) return false;
        lockpicks--;
        return true;
    }

    public Weapon.Bullet shoot(float speedX, float speedY) {
        return weapon.shoot(x, y, speedX, speedY, true);
    }

    public Armor getHelmet() {
        return helmet;
    }

    public Armor getBreastplate() {
        return breastplate;
    }

    @Override
    public double getDistance(float ox, float oy) {
        return sqrt((x + 60 - ox) * (x + 60 - ox) + (y + 75 - oy) * (y + 75 - oy));
    }

    @Override
    public void onMove() {

    }


    public List<Aid> getAids() {
        return aids;
    }

    @Override
    public void takeDamage(double damage, WeaponType type) {
        double breastplateArmor = breastplate == null ? 0 : breastplate.getArmor(type);
        double helmetArmor = helmet == null ? 0 : helmet.getArmor(type);
        double resultArmor = breastplateArmor + helmetArmor;
        double resistance = 1 - special.getResistance();
        Log.v(Constants.TAG_V + "_TAKE_DAMAGE", "Pure damage=" + damage + " resistance=" + resistance + " armor=" + resultArmor + " result = " + (damage - resultArmor) * resistance);
        if (damage < resultArmor) return;
        health -= (damage - resultArmor) * resistance;
        if (health < 0) health = 0;
    }

    @Override
    public boolean onShoot(Weapon.Bullet bullet) {
        if (bullet.fromHero) return false;
        takeDamage(bullet.damage, bullet.getType());
        return true;
    }

    @Override
    public void onDie() {

    }

    public void resetCoordinates() {
        x = 0;
        y = 0;
    }

    public void reset() {
        instance = new Hero();
    }

    public static Hero getInstance() {
        return instance;
    }
}
