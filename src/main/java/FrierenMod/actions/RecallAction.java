package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Iterator;

public class RecallAction extends AbstractGameAction {
    public static final String[] TEXT;
    private final AbstractPlayer player;
    private final int numberOfCards;
    private final boolean optional = false;

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
                if (this.player.exhaustPile.size() <= this.numberOfCards && !this.optional) {
                    ArrayList<AbstractCard> cardsToMove = new ArrayList<>();

                    AbstractCard c;
                    Iterator var5;
                    for(var5 = this.player.exhaustPile.group.iterator(); var5.hasNext(); c.retain = true) {
                        c = (AbstractCard)var5.next();
                        cardsToMove.add(c);
                    }

                    for(var5 = cardsToMove.iterator(); var5.hasNext(); c.lighten(false)) {
                        c = (AbstractCard)var5.next();
                        if (this.player.hand.size() < 10) {
                            this.player.hand.addToHand(c);
                            this.player.exhaustPile.removeCard(c);
                        }
                    }

                    this.isDone = true;
                } else {
                    if (this.numberOfCards == 1) {
                        if (this.optional) {
                            AbstractDungeon.gridSelectScreen.open(this.player.exhaustPile, this.numberOfCards, true, TEXT[0]);
                        } else {
                            AbstractDungeon.gridSelectScreen.open(this.player.exhaustPile, this.numberOfCards, TEXT[0], false);
                        }
                    } else if (this.optional) {
                        AbstractDungeon.gridSelectScreen.open(this.player.exhaustPile, this.numberOfCards, true, TEXT[1] + this.numberOfCards + TEXT[2]);
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
                Iterator<AbstractCard> var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                AbstractCard c;
                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    if (this.player.hand.size() < 10) {
                        this.player.hand.addToHand(c);
                        c.retain = true;
                        this.player.exhaustPile.removeCard(c);
                    }

                    c.lighten(false);
                    c.unhover();
                }

                for(var1 = this.player.exhaustPile.group.iterator(); var1.hasNext(); c.target_y = 0.0F) {
                    c = (AbstractCard)var1.next();
                    c.unhover();
                    c.target_x = (float) CardGroup.DISCARD_PILE_X;
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
