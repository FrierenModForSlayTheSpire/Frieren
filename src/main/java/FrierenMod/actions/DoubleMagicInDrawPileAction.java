package FrierenMod.actions;

import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.helpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;

public class DoubleMagicInDrawPileAction extends AbstractGameAction {
    private final boolean canGainMagic;

    public DoubleMagicInDrawPileAction(boolean canGainMagic) {
        this.canGainMagic = canGainMagic;
    }

    @Override
    public void update() {
        int draw = new ChantHelper().getMagicPowerNumInDrawPile();
        if(draw > 0){
            this.addToBot(new MakeMagicPowerInDrawPileAction(draw,canGainMagic));
        }
        this.isDone = true;
    }
}
