package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Factor010Power extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(Factor010Power.class.getSimpleName());

    public Factor010Power(AbstractCreature owner) {
        super(POWER_ID, owner, AbstractPower.PowerType.BUFF);
        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void onInitialApplication() {
        if (!owner.hasPower(Factor100Power.POWER_ID)) {
            this.addToBot(new RemoveSpecificPowerAction(owner, owner, Factor001Power.POWER_ID));
            this.addToBot(new RemoveSpecificPowerAction(owner, owner, Factor100Power.POWER_ID));
            this.addToBot(new RemoveSpecificPowerAction(owner, owner, Factor010Power.POWER_ID));
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.addToBot(new RemoveSpecificPowerAction(owner, owner, Factor001Power.POWER_ID));
        this.addToBot(new RemoveSpecificPowerAction(owner, owner, Factor100Power.POWER_ID));
        this.addToBot(new RemoveSpecificPowerAction(owner, owner, Factor010Power.POWER_ID));
    }

    public void updateDescription() {
        this.description = descriptions[0];
    }
}
