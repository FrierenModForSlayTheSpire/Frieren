package FrierenMod.actions;

import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.helpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;

public class DoubleMagicInDiscardPileAction extends AbstractGameAction {
    private final boolean canGainMagic;

    public DoubleMagicInDiscardPileAction(boolean canGainMagic) {
        this.canGainMagic = canGainMagic;
    }

    @Override
    public void update() {
        int discard = new ChantHelper().getMagicPowerNumInDiscardPile();
        if(discard > 0){
            this.addToBot(new MakeMagicPowerInDiscardAction(discard,canGainMagic));
        }
        this.isDone = true;
    }
}
