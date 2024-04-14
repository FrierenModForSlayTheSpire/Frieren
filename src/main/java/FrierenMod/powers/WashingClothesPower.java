package FrierenMod.powers;

import FrierenMod.actions.ModifyPowerStackAmtAction;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class WashingClothesPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(WashingClothesPower.class.getSimpleName());
    private int powerAmt;
    private int energyAmt;
    private int drawAmt;

    public WashingClothesPower(AbstractCreature owner, int powerAmt, int energyAmt, int drawAmt) {
        super(POWER_ID, owner, PowerType.BUFF);
        this.powerAmt = powerAmt;
        this.energyAmt = energyAmt;
        this.drawAmt = drawAmt;
        this.updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power instanceof WashingClothesPower) {
            this.powerAmt += ((WashingClothesPower) power).powerAmt;
            this.energyAmt += ((WashingClothesPower) power).energyAmt;
            this.drawAmt += ((WashingClothesPower) power).drawAmt;
            this.flash();
            this.updateDescription();
        }
    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        AbstractPower po = AbstractDungeon.player.getPower(ConcentrationPower.POWER_ID);
        this.addToBot(new ModifyPowerStackAmtAction(po, powerAmt, false));
        this.addToBot(new GainEnergyAction(this.energyAmt));
        if (this.drawAmt > 0)
            this.addToBot(new DrawCardAction(this.drawAmt));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    public void updateDescription() {
        if (this.drawAmt > 0) {
            this.description = String.format(descriptions[1], this.powerAmt, this.energyAmt, this.drawAmt);
        } else {
            this.description = String.format(descriptions[0], this.powerAmt, this.energyAmt);
        }
    }
}
