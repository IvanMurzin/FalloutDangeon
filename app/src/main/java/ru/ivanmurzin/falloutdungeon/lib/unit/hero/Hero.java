package ru.ivanmurzin.falloutdungeon.lib.unit.hero;

import ru.ivanmurzin.falloutdungeon.lib.game.object.Chest;
import ru.ivanmurzin.falloutdungeon.lib.item.aid.Aid;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.BodyArmor;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.armor.Helmet;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.artifact.Artifact;
import ru.ivanmurzin.falloutdungeon.lib.item.equipment.weapon.Weapon;
import ru.ivanmurzin.falloutdungeon.lib.unit.Unit;

public class Hero extends Unit {
    public final Special special;
    public final Experience experience;
    private Weapon weapon;
    private Helmet helmet;
    private BodyArmor bodyArmor;
    private Artifact[] artifacts;
    private int lockpicks;
    private Aid[] aid;


    public Hero() {
        super(100, 10);
        this.special = new Special();
        this.experience = new Experience();
    }

    public void getDamage(int damage) {
        health -= damage;
    }

    public void unlock(Chest chest) {

    }
}
