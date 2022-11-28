package ru.ivanmurzin.falloutdungeon.lib.unit.hero;

import static java.lang.Math.sqrt;

import ru.ivanmurzin.falloutdungeon.lib.game.object.Bullet;
import ru.ivanmurzin.falloutdungeon.lib.item.aid.Aid;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.BodyArmor;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.Helmet;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.artifact.Artifact;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.lib.unit.Unit;

public class Hero extends Unit {
    public static final Hero instance = new Hero();

    public final Special special;
    public final Experience experience;
    private Weapon weapon;
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

    public void getDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    @Override
    public double getDistance(float ox, float oy) {
        return sqrt((x + 60 - ox) * (x + 60 - ox) + (y + 75 - oy) * (y + 75 - oy));
    }

    @Override
    public void move() {

    }

    @Override
    public void getShoot(Bullet bullet) {
        if (bullet.fromHero) return;
        health -= 10;
    }
}
