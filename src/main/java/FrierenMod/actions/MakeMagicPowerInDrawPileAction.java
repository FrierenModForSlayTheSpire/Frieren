package FrierenMod.actions;

import FrierenMod.cards.tempCards.MagicPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;

public class MakeMagicPowerInDrawPileAction extends AbstractGameAction {
    private final int amt;
    private final boolean canGainMagic;

    public MakeMagicPowerInDrawPileAction(int amt, boolean canGainMagic) {
        this.amt = amt;
        this.canGainMagic = canGainMagic;
    }

    @Override
    public void update() {
        if(canGainMagic){
            if(amt < 6){
                this.addToBot(new MakeTempCardInDrawPileAction(new MagicPower(), amt,true,true));
            }else {
                int counts = amt / 5;
                int residue = amt % 5;
                for (int i = 0; i < counts; i++) {
                    this.addToBot(new MakeTempCardInDrawPileAction(new MagicPower(),5,true,true));
                }
                this.addToBot(new MakeTempCardInDrawPileAction(new MagicPower(),residue,true,true));
            }
        }
        this.isDone = true;
    }
}
