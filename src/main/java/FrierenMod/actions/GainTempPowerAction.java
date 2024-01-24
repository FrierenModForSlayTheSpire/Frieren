package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class GainTempPowerAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final int amount;
    public GainTempPowerAction(AbstractPlayer p, int amount){
        this.p = p;
        this.amount = amount;
    }
    @Override
    public void update() {
        this.addToBot(new ApplyPowerAction(this.p, this.p, new StrengthPower(this.p, this.amount), 1));
        this.addToBot(new ApplyPowerAction(this.p, this.p, new LoseStrengthPower(this.p, this.amount), 1));
        this.isDone = true;
    }
}
