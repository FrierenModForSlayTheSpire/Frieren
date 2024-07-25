package FrierenMod.gameHelpers;

import FrierenMod.ModManager;
import FrierenMod.actions.SealCardsAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.optionCards.ChantOptions.AbstractMagicFactor;
import FrierenMod.cards.optionCards.ChantOptions.BaseFactorAlpha;
import FrierenMod.cards.optionCards.ChantOptions.BaseFactorBeta;
import FrierenMod.cards.optionCards.ChantOptions.BaseFactorGamma;
import FrierenMod.enums.CharacterEnums;
import FrierenMod.patches.fields.MagicFactorDeckField;
import basemod.interfaces.OnPlayerTurnStartSubscriber;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PostCreateStartingDeckSubscriber;
import basemod.interfaces.StartGameSubscriber;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class HookHelper implements OnPlayerTurnStartSubscriber, OnStartBattleSubscriber, PostCreateStartingDeckSubscriber, StartGameSubscriber {
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
    }

    @Override
    public void receivePostCreateStartingDeck(AbstractPlayer.PlayerClass playerClass, CardGroup cardGroup) {
        if (playerClass == CharacterEnums.FRIEREN) {
            MagicFactorDeckField.magicFactorDeck.set(AbstractDungeon.player, new CardGroup(CardGroup.CardGroupType.UNSPECIFIED));
            BaseFactorAlpha alpha = new BaseFactorAlpha();
            alpha.setCurrentSlot(0);
            BaseFactorBeta beta = new BaseFactorBeta();
            beta.setCurrentSlot(1);
            BaseFactorGamma gamma = new BaseFactorGamma();
            gamma.setCurrentSlot(2);
            MagicFactorDeckField.getDeck().addToTop(alpha);
            MagicFactorDeckField.getDeck().addToTop(beta);
            MagicFactorDeckField.getDeck().addToTop(gamma);
            MagicFactorHelper.saveAllFactors();
        }
    }

    @Override
    public void receiveStartGame() {
        MagicFactorDeckField.magicFactorDeck.set(AbstractDungeon.player, new CardGroup(CardGroup.CardGroupType.UNSPECIFIED));
        if (ModManager.saveData.containsKey(MagicFactorHelper.SAVE_NAME)) {
            for (AbstractMagicFactor f : MagicFactorHelper.getAllFactors()) {
                MagicFactorDeckField.getDeck().addToTop(f);
            }
        }
    }
}
