package FrierenMod.gameHelpers;

import FrierenMod.actions.SealCardsAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CharacterEnums;
import FrierenMod.patches.fields.MagicDeckField;
import FrierenMod.patches.fields.RandomField;
import FrierenMod.patches.fields.RandomField2;
import FrierenMod.rewards.MagicItemReward;
import FrierenMod.ui.panels.MagicDeckPanel;
import FrierenMod.utils.Config;
import FrierenMod.utils.Log;
import basemod.ReflectionHacks;
import basemod.TopPanelGroup;
import basemod.TopPanelItem;
import basemod.interfaces.*;
import basemod.patches.com.megacrit.cardcrawl.helpers.TopPanel.TopPanelHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import java.util.ArrayList;

public class HookHelper implements OnPlayerTurnStartSubscriber, OnStartBattleSubscriber, PostCreateStartingDeckSubscriber, PostBattleSubscriber, StartGameSubscriber {
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
        MagicDeckField.magicDeck.set(AbstractDungeon.player, new CardGroup(CardGroup.CardGroupType.UNSPECIFIED));
        if (playerClass == CharacterEnums.FRIEREN) {
            MagicItemHelper.save();
        }
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        if (AbstractDungeon.player.chosenClass != CharacterEnums.FRIEREN)
            return;
        AbstractRoom currRoom = AbstractDungeon.getCurrRoom();
        int chance = 0;
        if (currRoom instanceof MonsterRoomElite) {
            chance = 70;
            chance += RandomField2.getBlizzardMagicItemMod();
        } else if (currRoom instanceof MonsterRoom) {
            if (!AbstractDungeon.getMonsters().haveMonstersEscaped()) {
                chance = 40;
                chance += RandomField2.getBlizzardMagicItemMod();
            }
        } else if (currRoom instanceof EventRoom) {
            chance = 40;
            chance += RandomField2.getBlizzardMagicItemMod();
        }
        Log.logger.info("MAGIC ITEM CHANCE: {}", chance);
        if (RandomField.getMagicItemRng().random(0, 99) < chance || Config.IN_DEV) {
            MagicItemReward.addMagicItemRewardToRoom();
            RandomField2.addBlizzardMagicItemMod(-10);
        } else {
            RandomField2.addBlizzardMagicItemMod(10);
        }
    }

    @Override
    public void receiveStartGame() {
        ArrayList<TopPanelItem> items = ReflectionHacks.getPrivate(TopPanelHelper.topPanelGroup, TopPanelGroup.class, "topPanelItems");
        if (AbstractDungeon.player.chosenClass != CharacterEnums.FRIEREN)
            items.removeIf(topPanelItem -> topPanelItem instanceof MagicDeckPanel);
        else {
            boolean hasPanel = false;
            for (TopPanelItem item : items)
                if (item instanceof MagicDeckPanel) {
                    hasPanel = true;
                    break;
                }
            if (!hasPanel)
                items.add(new MagicDeckPanel());
        }
    }
}
