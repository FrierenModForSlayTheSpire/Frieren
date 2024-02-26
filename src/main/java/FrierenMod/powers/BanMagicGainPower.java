package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class BanMagicGainPower extends AbstractFrierenPower {
    public static final String POWER_ID = ModInformation.makeID(BanMagicGainPower.class.getSimpleName());
    public BanMagicGainPower(AbstractCreature owner) {
        super(POWER_ID, owner, PowerType.DEBUFF);
    }
    public void updateDescription() {
        this.description = descriptions[0];
    }
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
}
