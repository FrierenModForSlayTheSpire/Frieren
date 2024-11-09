package FrierenMod.powers;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class WithNoStopPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(WithNoStopPower.class.getSimpleName());

    public WithNoStopPower(int amount) {
        super(POWER_ID, AbstractDungeon.player, amount, PowerType.BUFF);
        this.updateDescription();
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        this.flash();
        this.addToBot(new ApplyPowerAction(owner, owner, new ConcentrationPower(2)));
        this.addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, this, 1));
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
