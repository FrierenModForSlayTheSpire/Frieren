package FrierenMod.actions;

import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;

public class TongueTwisterSpellAction extends AbstractGameAction {
    private final int magicNumber;

    public TongueTwisterSpellAction(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public void update() {
        if(ChantHelper.getMagicPowerNumInHand() > 0){
            this.addToBot(new DrawCardAction(this.magicNumber));
        }
        else {
            this.addToBot(new DrawCardAction(this.magicNumber + 1));
        }
        this.isDone =true;
    }
}
