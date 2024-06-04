package FrierenMod.powers;

import FrierenMod.actions.DrawLegendarySpellAction;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class SimmeringPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(SimmeringPower.class.getSimpleName());

    public SimmeringPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
        this.updateDescription();
    }

    @Override
    public void afterChant() {
        this.flash();
        this.addToBot(new DrawLegendarySpellAction(this.amount));
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
