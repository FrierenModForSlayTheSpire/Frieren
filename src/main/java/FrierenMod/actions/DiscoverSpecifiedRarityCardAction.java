package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;

public class DiscoverSpecifiedRarityCardAction extends AbstractGameAction {
    private boolean retrieveCard = false;
    private final AbstractCard.CardRarity cardRarity;
    public DiscoverSpecifiedRarityCardAction(AbstractCard.CardRarity rarity,int amount) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.cardRarity = rarity;
        this.amount = amount;
    }
    public void update() {
        ArrayList<AbstractCard> generatedCards;
        generatedCards = generateCardChoices(this.cardRarity);
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, CardRewardScreen.TEXT[1], true);
            tickDuration();
            return;
        }
        if (!this.retrieveCard) {
            if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                AbstractCard disCard2 = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                if (AbstractDungeon.player.hasPower("MasterRealityPower")) {
                    disCard.upgrade();
                    disCard2.upgrade();
                }
                disCard.setCostForTurn(0);
                disCard2.setCostForTurn(0);
                disCard.current_x = -1000.0F * Settings.xScale;
                disCard2.current_x = -1000.0F * Settings.xScale + AbstractCard.IMG_HEIGHT_S;
                if (this.amount == 1) {
                    if (AbstractDungeon.player.hand.size() < 10) {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                    } else {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                    }
                    disCard2 = null;
                } else if (AbstractDungeon.player.hand.size() + this.amount <= 10) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard2, Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                } else if (AbstractDungeon.player.hand.size() == 9) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard2, Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                } else {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard2, Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                }
                AbstractDungeon.cardRewardScreen.discoveryCard = null;
            }
            this.retrieveCard = true;
        }
        tickDuration();
    }

    private ArrayList<AbstractCard> generateCardChoices(AbstractCard.CardRarity rarity) {
        ArrayList<AbstractCard> derp = new ArrayList<>();
        while (derp.size() != 3) {
            boolean dupe = false;
            AbstractCard tmp = null;
            tmp = returnTrulyRandomCardInCombat(rarity);
            for (AbstractCard c : derp) {
                if (c.cardID.equals(tmp.cardID)) {
                    dupe = true;
                    break;
                }
            }
            if (!dupe)
                derp.add(tmp.makeCopy());
        }
        return derp;
    }
    private static AbstractCard returnTrulyRandomCardInCombat(AbstractCard.CardRarity rarity) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        switch (rarity){
            case COMMON:
                for (AbstractCard c : srcCommonCardPool.group) {
                    if (!c.hasTag(AbstractCard.CardTags.HEALING))
                        list.add(c);
                }
                break;
            case UNCOMMON:
                for (AbstractCard c : srcUncommonCardPool.group) {
                    if (!c.hasTag(AbstractCard.CardTags.HEALING))
                        list.add(c);
                }
                break;
            case RARE:
                for (AbstractCard c : srcRareCardPool.group) {
                    if (!c.hasTag(AbstractCard.CardTags.HEALING))
                        list.add(c);
                }
                break;
            default:
                break;
        }
        return list.get(cardRandomRng.random(list.size() - 1));
    }
}

