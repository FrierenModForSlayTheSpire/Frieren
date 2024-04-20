package FrierenMod.gameHelpers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ActionHelper {
    public static void addToBotAbstract(Lambda func) {
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                func.run();
                isDone = true;
            }
        });
    }

    public static void addToTopAbstract(Lambda  func) {
        AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
            @Override
            public void update() {
                func.run();
                isDone = true;
            }
        });
    }

    public interface Lambda extends Runnable {}
}
