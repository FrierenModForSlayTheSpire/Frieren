package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ModifyPowerStackAmtAction extends AbstractGameAction {
    private final AbstractPower power;
    private final int amt;
    private final boolean isNewAmt;

    public ModifyPowerStackAmtAction(AbstractPower power, int amt) {
        this.power = power;
        this.amt = amt;
        this.isNewAmt = false;
    }
    public ModifyPowerStackAmtAction(AbstractPower power, int amt, boolean newAmt) {
        this.power = power;
        this.amt = amt;
        this.isNewAmt = newAmt;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if(this.amt != 0){
            if(this.isNewAmt)
                this.power.amount = amt;
            else {
                this.power.amount += this.amt;
            }
            this.power.flash();
            this.power.updateDescription();
            if (this.power.amount == 0)
                this.addToBot(new RemoveSpecificPowerAction(p, p, this.power));
        }
        this.isDone = true;
    }
}
