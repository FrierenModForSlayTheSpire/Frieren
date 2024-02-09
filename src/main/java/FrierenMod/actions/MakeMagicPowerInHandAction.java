package FrierenMod.actions;

import FrierenMod.cards.tempCards.MagicPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;

public class MakeMagicPowerInHandAction extends AbstractGameAction {
    private final int amt;
    private final boolean canGainMagic;

    public MakeMagicPowerInHandAction(int amt, boolean canGainMagic) {
        this.amt = amt;
        this.canGainMagic = canGainMagic;
    }

    @Override
    public void update() {
        if(canGainMagic){
            this.addToBot(new MakeTempCardInHandAction(new MagicPower(),this.amt));
        }
        this.isDone = true;
    }
}
