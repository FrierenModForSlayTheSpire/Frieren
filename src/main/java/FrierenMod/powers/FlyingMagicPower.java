package FrierenMod.powers;

import FrierenMod.actions.ExhaustManaInCardGroupAction;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class FlyingMagicPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(FlyingMagicPower.class.getSimpleName());

    public FlyingMagicPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.DEBUFF);
        this.updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        this.flash();
        this.addToBot(new ExhaustManaInCardGroupAction(this.amount, 2));
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }

}