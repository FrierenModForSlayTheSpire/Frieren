package FrierenMod.actions;

import FrierenMod.gameHelpers.CombatHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class DoubleManaInDiscardPileAction extends AbstractGameAction {
    @Override
    public void update() {
        int discard = CombatHelper.getManaNumInDiscardPile();
        if (discard > 0) {
            this.addToBot(new MakeManaInDiscardAction(discard));
        }
        this.isDone = true;
    }
}
