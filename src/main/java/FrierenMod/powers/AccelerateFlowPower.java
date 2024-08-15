package FrierenMod.powers;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AccelerateFlowPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(AccelerateFlowPower.class.getSimpleName());
    private final AbstractPlayer p = AbstractDungeon.player;

    public AccelerateFlowPower(AbstractCreature owner) {
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
    public void onAfterCardPlayed(AbstractCard usedCard) {
        giveTags();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        removeTags();
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    @Override
    public void onRemove() {
        removeTags();
    }

    public void updateDescription() {
        this.description = descriptions[0];
    }

    private void giveCardTagsInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if (c.hasTag(AbstractBaseCard.Enum.SYNCHRO) && !c.hasTag(AbstractBaseCard.Enum.ACCEL_SYNCHRO)) {
                if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                    c.superFlash();
                }
                c.tags.add(AbstractBaseCard.Enum.ACCEL_SYNCHRO);
            }
        }
    }

    private void giveTags() {
        giveCardTagsInGroup(p.drawPile);
        giveCardTagsInGroup(p.hand);
        giveCardTagsInGroup(p.discardPile);
        giveCardTagsInGroup(p.exhaustPile);
    }

    private void removeCardTagsInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if (c.hasTag(AbstractBaseCard.Enum.SYNCHRO) && c.hasTag(AbstractBaseCard.Enum.ACCEL_SYNCHRO)) {
                c.tags.remove(AbstractBaseCard.Enum.ACCEL_SYNCHRO);
            }
        }
    }

    private void removeTags() {
        removeCardTagsInGroup(p.drawPile);
        removeCardTagsInGroup(p.hand);
        removeCardTagsInGroup(p.discardPile);
        removeCardTagsInGroup(p.exhaustPile);
    }
}