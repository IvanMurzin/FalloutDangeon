package ru.ivanmurzin.falloutdungeon.lib.unit.hero;

import static java.lang.Math.sqrt;

import android.util.Log;

import ru.ivanmurzin.falloutdungeon.Constants;
import ru.ivanmurzin.falloutdungeon.lib.item.aid.Aid;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.BodyArmor;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.Helmet;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.artifact.Artifact;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Pistol;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.WeaponType;
import ru.ivanmurzin.falloutdungeon.lib.unit.Unit;

public class Hero extends Unit {
    public static final Hero instance = new Hero();

    public final Special special;
    public final Experience experience;
    private final Weapon weapon;
    private Helmet helmet;
    private BodyArmor bodyArmor;
    private Artifact[] artifacts;
    private int lockpicks;
    private Aid[] aid;

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
        lockpicks = 5;
        experience = new Experience();
        weapon = new Pistol();
    }

    public void addLockpicks(int lockpicks) {
        this.lockpicks += lockpicks;
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


    @Override
    public double getDistance(float ox, float oy) {
        return sqrt((x + 60 - ox) * (x + 60 - ox) + (y + 75 - oy) * (y + 75 - oy));
    }

    @Override
    public void onMove() {

    }

    @Override
    public void takeDamage(double damage, WeaponType type) {
        double resistance = 1 - special.getResistance();
        health -= damage * resistance;
        Log.v(Constants.TAG, "Pure damage=" + damage + " resistance=" + resistance + " result=" + damage * resistance);
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
}
