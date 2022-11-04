package ru.ivanmurzin.falloutdungeon.lib.unit.hero;

public class SpecialItem {
    public final String shortName;
    public final String fullName;
    private int value;

    public SpecialItem(String shortName, String fullName, int value) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    protected void setValue(int value) {
        this.value = value;
    }
}
