package FrierenMod.actions;

import FrierenMod.gameHelpers.CombatHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class DoubleManaInDrawPileAction extends AbstractGameAction {
    @Override
    public void update() {
        int draw = CombatHelper.getManaNumInDrawPile();
        if(draw > 0){
            this.addToBot(new MakeManaInDrawPileAction(draw));
        }
        this.isDone = true;
    }
}
