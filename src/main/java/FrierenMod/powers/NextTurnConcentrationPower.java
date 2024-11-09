package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class NextTurnConcentrationPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(NextTurnConcentrationPower.class.getSimpleName());

    public NextTurnConcentrationPower(int amount) {
        super(POWER_ID, AbstractDungeon.player, amount, PowerType.BUFF);
        this.updateDescription();
    }

    @Override
    public void onEnergyRecharge() {
        this.addToBot(new ApplyPowerAction(owner, owner, new ConcentrationPower(amount)));
        this.flash();
        this.addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], amount);
    }
}
