package ru.ivanmurzin.falloutdungeon.lib.game.object.chest;

import android.util.Log;

import ru.ivanmurzin.falloutdungeon.Constants;
import ru.ivanmurzin.falloutdungeon.controller.Logger;
import ru.ivanmurzin.falloutdungeon.controller.RandomController;
import ru.ivanmurzin.falloutdungeon.lib.game.IntractableGameObject;
import ru.ivanmurzin.falloutdungeon.lib.item.Item;
import ru.ivanmurzin.falloutdungeon.lib.unit.hero.Hero;

public class Chest extends IntractableGameObject {
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

    @Override
    public void action(Logger logger) {
        switch (state) {
            case Closed:
                boolean decreaseLockpick = Hero.instance.decreaseLockpick();
                if (!decreaseLockpick) {
                    logger.notifyError("Недостаточно отмычек");
                    return;
                }
                int hackChance = Hero.instance.special.getHackChance(difficulty);
                Log.d(Constants.TAG, "action: " + hackChance);
                if (RandomController.isSuccess(hackChance)) {
                    logger.notifySuccess("Успех! Отмычек в запасе: " + Hero.instance.getLockpicks());
                    state = ChestState.Opened;
                } else {
                    logger.notifyWarning("Провал! Отмычек в запасе: " + Hero.instance.getLockpicks());
                }
                break;
            case Opened:
                state = ChestState.Clear;
                break;
            case Clear:
                break;
        }
    }
}
