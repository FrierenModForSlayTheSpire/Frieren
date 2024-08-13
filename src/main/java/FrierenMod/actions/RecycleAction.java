package FrierenMod.actions;

import FrierenMod.gameHelpers.CombatHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class RecycleAction extends AbstractGameAction {
    private final int magicNumber;

    public RecycleAction(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public void update() {
        int exhaust = CombatHelper.getManaNumInDiscardPile();
        if(exhaust > 0){
            this.addToBot(new MakeManaInDrawPileAction(exhaust/this.magicNumber));
        }
        this.isDone = true;
    }
}
