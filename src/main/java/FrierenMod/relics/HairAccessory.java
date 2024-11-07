package FrierenMod.relics;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.powers.ConcentrationPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class HairAccessory extends AbstractBaseRelic {
    public static final String ID = ModInformation.makeID(HairAccessory.class.getSimpleName());

    public HairAccessory() {
        super(ID, RelicTier.STARTER);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new HairAccessory();
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if (card.hasTag(AbstractBaseCard.Enum.MANA))
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ConcentrationPower(1)));
    }
}
