package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class MagicInsteadOfCostPower extends AbstractFrierenPower {
    public static final String POWER_ID = ModInformation.makeID(MagicInsteadOfCostPower.class.getSimpleName());
    public MagicInsteadOfCostPower(AbstractCreature owner) {
        super(POWER_ID, owner, PowerType.BUFF);
    }
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
    public void updateDescription() {
        this.description = String.format(descriptions[0]);
    }
}