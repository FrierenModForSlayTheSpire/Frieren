package FrierenMod.powers;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SkilledMagePower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(SkilledMagePower.class.getSimpleName());

    public SkilledMagePower(int amount) {
        super(POWER_ID, AbstractDungeon.player, amount, PowerType.BUFF);
        this.updateDescription();
    }

    @Override
    public void onAfterCardPlayed(AbstractCard card) {
        if (card.hasTag(AbstractBaseCard.Enum.SPEED) || card.hasTag(AbstractBaseCard.Enum.FUSION)) {
            this.flash();
            this.addToBot(new GainBlockAction(AbstractDungeon.player, this.amount));
        }
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], amount);
    }
}
