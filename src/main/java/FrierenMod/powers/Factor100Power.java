package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Factor100Power extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(Factor100Power.class.getSimpleName());

    public Factor100Power(AbstractCreature owner) {
        super(POWER_ID, owner, AbstractPower.PowerType.BUFF);
        this.updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        this.addToBot(new RemoveSpecificPowerAction(owner, owner, Factor001Power.POWER_ID));
        this.addToBot(new RemoveSpecificPowerAction(owner, owner, Factor100Power.POWER_ID));
        this.addToBot(new RemoveSpecificPowerAction(owner, owner, Factor010Power.POWER_ID));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    public void updateDescription() {
        this.description = descriptions[0];
    }
}
