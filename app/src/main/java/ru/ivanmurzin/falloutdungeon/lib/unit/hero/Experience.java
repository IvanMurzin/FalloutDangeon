package ru.ivanmurzin.falloutdungeon.lib.unit.hero;

public class Experience {
    private int current = 0;
    private int required = 110;
    private int level = 1;


    public int getCurrent() {
        return current;
    }

    public int getRequired() {
        return required;
    }

    public int getLevel() {
        return level;
    }

    public void accrueExperience(int experience) {
        this.current += experience;
        if (current > required) {
            level += current / required;
            current %= required;
            required = level * 110;
        }
    }
}
