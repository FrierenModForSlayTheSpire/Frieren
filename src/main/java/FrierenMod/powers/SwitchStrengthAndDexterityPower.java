package FrierenMod.powers;

import FrierenMod.actions.SwitchStrengthAndDexterityAction;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class SwitchStrengthAndDexterityPower extends AbstractFrierenPower {
    public static final String POWER_ID = ModInformation.makeID(SwitchStrengthAndDexterityPower.class.getSimpleName());
    public SwitchStrengthAndDexterityPower(AbstractCreature owner) {
        super(POWER_ID, owner, PowerType.BUFF);
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            this.flash();
            this.addToBot(new SwitchStrengthAndDexterityAction());
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
    }
    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount * 2);
    }
}
