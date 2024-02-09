package FrierenMod.actions;

import FrierenMod.cards.tempCards.MagicPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;

public class MakeMagicPowerInDiscardAction extends AbstractGameAction {
    private final int amt;
    private final boolean canGainMagic;

    public MakeMagicPowerInDiscardAction(int amt, boolean canGainMagic) {
        this.amt = amt;
        this.canGainMagic = canGainMagic;
    }

    @Override
    public void update() {
        if(canGainMagic){
            if(amt < 6){
                this.addToBot(new MakeTempCardInDiscardAction(new MagicPower(), amt));
            }else {
                int counts = amt / 5;
                int residue = amt % 5;
                for (int i = 0; i < counts; i++) {
                    this.addToBot(new MakeTempCardInDiscardAction(new MagicPower(),5));
                }
                this.addToBot(new MakeTempCardInDiscardAction(new MagicPower(),residue));
            }
        }
        this.isDone = true;
    }
}
