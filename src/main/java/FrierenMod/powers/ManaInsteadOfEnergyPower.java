package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class ManaInsteadOfEnergyPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(ManaInsteadOfEnergyPower.class.getSimpleName());
    public ManaInsteadOfEnergyPower(AbstractCreature owner) {
        super(POWER_ID, owner, PowerType.BUFF);
        this.updateDescription();
    }
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
    public void updateDescription() {
        this.description = String.format(descriptions[0]);
    }
}