package ru.ivanmurzin.falloutdungeon.lib.game.object.chest;

import ru.ivanmurzin.falloutdungeon.controller.Logger;
import ru.ivanmurzin.falloutdungeon.lib.InteractiveGameObject;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;
import ru.ivanmurzin.falloutdungeon.util.RandomGenerator;

public class Chest extends InteractiveGameObject {
    public final Item item;
    public final int difficulty;
    public final ChestType type;
    private ChestState state = ChestState.Closed;

    public Chest(int x, int y, Item item, int difficulty, ChestType type) {
        super(x, y);
        this.item = item;
        this.difficulty = difficulty;
        this.type = type;
    }

    public ChestState getState() {
        return state;
    }

    public void setState(ChestState state) {
        this.state = state;
    }

    @Override
    public void action(Logger logger) {
        if (state == ChestState.Closed) {
            boolean decreaseLockpick = Hero.instance.decreaseLockpick();
            if (!decreaseLockpick) {
                logger.notifyError("Недостаточно отмычек");
                return;
            }
            int hackChance = Hero.instance.special.getHackChance(difficulty);
            if (RandomGenerator.isSuccess(hackChance)) {
                logger.notifySuccess("Успех! Отмычек в запасе: " + Hero.instance.getLockpicks());
                state = ChestState.Opened;
            } else {
                logger.notifyWarning("Провал! Отмычек в запасе: " + Hero.instance.getLockpicks());
            }
        }
    }
}
