package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class MageStyleChokePower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(MageStyleChokePower.class.getSimpleName());

    public MageStyleChokePower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.DEBUFF);
        this.updateDescription();
    }

    public void atStartOfTurn() {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    @Override
    public void afterSynchroFinished() {
        this.flash();
        this.addToBot(new LoseHPAction(this.owner, null, this.amount));
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }

}