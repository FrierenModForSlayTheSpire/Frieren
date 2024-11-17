package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ConfidencePower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(ConfidencePower.class.getSimpleName());

    public ConfidencePower() {
        super(POWER_ID, AbstractDungeon.player, PowerType.BUFF);
        this.updateDescription();
    }

    public void atStartOfTurn() {
        this.flash();
        this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, ConcentrationPower.POWER_ID));
    }

    @Override
    public int modifyRaidTriggerTimes(boolean bool) {
        if (bool) {
            this.flash();
            return 1;
        }
        return 0;
    }

    public void updateDescription() {
        this.description = descriptions[0];
    }
}
