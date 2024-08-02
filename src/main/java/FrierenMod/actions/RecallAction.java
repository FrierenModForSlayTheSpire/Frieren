package FrierenMod.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class RecallAction extends AbstractGameAction {
    public static final String[] TEXT;
    private final AbstractPlayer player;
    private final int numberOfCards;

    public RecallAction(int numberOfCards) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            if (!this.player.exhaustPile.isEmpty() && this.numberOfCards > 0) {
                if (this.player.exhaustPile.size() <= this.numberOfCards) {
                    ArrayList<AbstractCard> cardsToMove = new ArrayList<>();
                    for (AbstractCard c : this.player.exhaustPile.group) {
                        c.unhover();
                        c.unfadeOut();
                        c.retain = true;
                        cardsToMove.add(c);
                    }
                    for (AbstractCard c : cardsToMove) {
                        if (this.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
                            c.lighten(false);
                            player.hand.addToHand(c);
                            player.exhaustPile.removeCard(c);
                        }
                    }
                    this.isDone = true;
                } else {
                    if (this.numberOfCards == 1) {
                        AbstractDungeon.gridSelectScreen.open(this.player.exhaustPile, this.numberOfCards, TEXT[0], false);
                    } else {
                        AbstractDungeon.gridSelectScreen.open(this.player.exhaustPile, this.numberOfCards, TEXT[1] + this.numberOfCards + TEXT[2], false);
                    }
                    this.tickDuration();
                }
            } else {
                this.isDone = true;
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    if (this.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
                        this.player.hand.addToHand(c);
                        c.retain = true;
                        c.unfadeOut();
                        c.unhover();
                        this.player.exhaustPile.removeCard(c);
                    }
                }
                for (AbstractCard c : player.exhaustPile.group) {
                    c.unhover();
                    c.target_x = (float) CardGroup.DISCARD_PILE_X;
                    c.target_y = 0.0F;
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }
            this.tickDuration();
        }
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString("BetterToHandAction").TEXT;
    }
}
