package FrierenMod.gameHelpers;

import FrierenMod.actions.SealCardsAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.optionCards.magicItems.BaseFactorAlpha;
import FrierenMod.cards.optionCards.magicItems.BaseFactorBeta;
import FrierenMod.cards.optionCards.magicItems.BaseFactorGamma;
import FrierenMod.enums.CharacterEnums;
import FrierenMod.patches.fields.MagicItemBagField;
import FrierenMod.rewards.MagicItemReward;
import FrierenMod.utils.Config;
import basemod.interfaces.OnPlayerTurnStartSubscriber;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PostCreateStartingDeckSubscriber;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

public class HookHelper implements OnPlayerTurnStartSubscriber, OnStartBattleSubscriber, PostCreateStartingDeckSubscriber{
    @Override
    public void receiveOnPlayerTurnStart() {
        try {
            resetCardCost();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void resetBackFireCardCostInCardGroup(CardGroup group) throws InstantiationException, IllegalAccessException {
        for (AbstractCard c : group.group) {
            if (c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isCostResetCard) {
                Class<? extends AbstractBaseCard> cardClass = (Class<? extends AbstractBaseCard>) c.getClass();
                c.cost = cardClass.newInstance().cost;
                c.costForTurn = c.cost;
                c.isCostModified = false;
            }
        }
    }

    private void resetCardCost() throws InstantiationException, IllegalAccessException {
        AbstractPlayer p = AbstractDungeon.player;
        resetBackFireCardCostInCardGroup(p.hand);
        resetBackFireCardCostInCardGroup(p.discardPile);
        resetBackFireCardCostInCardGroup(p.drawPile);
        resetBackFireCardCostInCardGroup(p.exhaustPile);
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        AbstractDungeon.actionManager.addToBottom(new SealCardsAction());
        int chance = 0;
        if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) {
            chance = 60;
        } else if (AbstractDungeon.getCurrRoom() instanceof MonsterRoom) {
            if (!AbstractDungeon.getMonsters().haveMonstersEscaped()) {
                chance = 30;
            }
        } else if (AbstractDungeon.getCurrRoom() instanceof EventRoom) {
            chance = 30;
        }
        if (AbstractDungeon.cardRandomRng.random(0, 99) < chance || Config.IN_DEV) {
            MagicItemReward.addMagicItemRewardToRoom();
        }
    }

    @Override
    public void receivePostCreateStartingDeck(AbstractPlayer.PlayerClass playerClass, CardGroup cardGroup) {
        if (playerClass == CharacterEnums.FRIEREN) {
            MagicItemBagField.magicItemBag.set(AbstractDungeon.player, new CardGroup(CardGroup.CardGroupType.UNSPECIFIED));
            BaseFactorAlpha alpha = new BaseFactorAlpha();
            alpha.setCurrentSlot(0);
            BaseFactorBeta beta = new BaseFactorBeta();
            beta.setCurrentSlot(1);
            BaseFactorGamma gamma = new BaseFactorGamma();
            gamma.setCurrentSlot(2);
            MagicItemBagField.getBag().addToTop(alpha);
            MagicItemBagField.getBag().addToTop(beta);
            MagicItemBagField.getBag().addToTop(gamma);
            MagicItemHelper.save();
        }
    }

}
