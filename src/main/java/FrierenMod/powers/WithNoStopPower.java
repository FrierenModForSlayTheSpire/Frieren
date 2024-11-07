package FrierenMod.powers;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class WithNoStopPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(WithNoStopPower.class.getSimpleName());

    public WithNoStopPower(int amount) {
        super(POWER_ID, AbstractDungeon.player, amount, PowerType.BUFF);
        this.updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.hasTag(AbstractBaseCard.Enum.SPEED))
            card.tags.add(AbstractBaseCard.Enum.SPEED);
        this.addToBot(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, this, 1));
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
