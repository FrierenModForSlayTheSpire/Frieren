package FrierenMod.actions;

import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;

public class TwisterMagicAction extends AbstractGameAction {
    private final int magicNumber;

    public TwisterMagicAction(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public void update() {
        if(new ChantHelper().getMagicPowerNumInHand() > 0){
            this.addToBot(new DrawCardAction(this.magicNumber));
        }
        else {
            this.addToBot(new DrawCardAction(this.magicNumber + 1));
        }
        this.isDone =true;
    }
}
