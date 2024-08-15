package FrierenMod.powers;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ThunderPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(ThunderPower.class.getSimpleName());
    private final AbstractPlayer p = AbstractDungeon.player;

    public ThunderPower(AbstractCreature owner) {
        super(POWER_ID, owner, PowerType.BUFF);
        this.updateDescription();
        this.priority = 11;
    }

    @Override
    public void onInitialApplication() {
        giveTags();
    }

    @Override
    public void onDrawOrDiscard() {
        giveTags();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        giveTags();
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        giveTags();
    }

    public void updateDescription() {
        this.description = descriptions[0];
    }

    private void giveCardTagsInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if (c.hasTag(AbstractBaseCard.Enum.SYNCHRO) && !c.hasTag(AbstractBaseCard.Enum.LIMIT_OVER_SYNCHRO)) {
                c.tags.add(AbstractBaseCard.Enum.LIMIT_OVER_SYNCHRO);
                if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                    c.superFlash();
                }
            }
        }
    }

    public void giveTags() {
        giveCardTagsInGroup(p.drawPile);
        giveCardTagsInGroup(p.hand);
        giveCardTagsInGroup(p.discardPile);
        giveCardTagsInGroup(p.exhaustPile);
    }

}