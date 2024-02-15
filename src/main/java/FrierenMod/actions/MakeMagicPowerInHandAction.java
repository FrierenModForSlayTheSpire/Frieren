package FrierenMod.actions;

import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.gameHelpers.HardCodedPowerHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;

public class MakeMagicPowerInHandAction extends AbstractGameAction {
    private final int amt;

    public MakeMagicPowerInHandAction(int amt) {
        this.amt = amt;
    }

    @Override
    public void update() {
        if(HardCodedPowerHelper.dontHaveBanMagicGainPower()){
            this.addToBot(new MakeTempCardInHandAction(new MagicPower(),this.amt));
        }
        this.isDone = true;
    }
}
