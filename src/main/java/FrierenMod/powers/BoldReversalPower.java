package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BoldReversalPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(BoldReversalPower.class.getSimpleName());

    public BoldReversalPower(int amount) {
        super(POWER_ID, AbstractDungeon.player, amount, PowerType.BUFF);
        this.updateDescription();
    }

    @Override
    public void afterRaidTriggered() {
        addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, this, 1));
    }

    @Override
    public int modifyRaidTriggerTimes() {
        return 1;
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], amount);
    }
}
