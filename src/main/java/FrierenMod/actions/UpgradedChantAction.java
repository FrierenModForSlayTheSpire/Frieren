package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class UpgradedChantAction extends AbstractGameAction {
    @Override
    public void update() {
        this.isDone = true;
    }
}
