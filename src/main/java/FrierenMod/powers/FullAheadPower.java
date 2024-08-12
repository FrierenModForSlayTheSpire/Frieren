package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;


public class FullAheadPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(FullAheadPower.class.getSimpleName());
    public boolean takeEffect;

    public FullAheadPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
        this.takeEffect = false;
        this.updateDescription();
    }

    public void afterChantFinished() {
        if (!takeEffect) {
            this.flash();
            this.addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, this.amount), this.amount));
            this.addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, this.amount), this.amount));
            this.takeEffect = true;
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.takeEffect = false;
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount, this.amount);
    }
}