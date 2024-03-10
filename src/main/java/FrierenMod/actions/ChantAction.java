package FrierenMod.actions;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.optionCards.ChantDiscardPile;
import FrierenMod.cards.optionCards.ChantDrawPile;
import FrierenMod.cards.optionCards.ChantHand;
import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import static FrierenMod.gameHelpers.HardCodedPowerHelper.CHANT_WITHOUT_MAGIC;

public class ChantAction extends AbstractGameAction {
    private final int x;
    private final boolean giveCard;
    public ChantAction(int x) {
        this.x = x;
        this.giveCard = false;
    }
    public ChantAction(int x, boolean giveCard) {
        this.x = x;
        this.giveCard = giveCard;
    }
    @Override
    public void update() {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        if(!AbstractDungeon.player.hasPower(CHANT_WITHOUT_MAGIC)){
            if (ChantHelper.canChantFromDrawPile(this.x)) {
                ChantDrawPile c = new ChantDrawPile();
                c.block = c.baseBlock = x;
                c.magicNumber = c.baseMagicNumber = x;
                c.applyPowers();
                stanceChoices.add(c);
            }
            if (ChantHelper.canChantFromHand(this.x)) {
                ChantHand c = new ChantHand(giveCard);
                c.magicNumber = c.baseMagicNumber = x;
                stanceChoices.add(c);
            }
            if (ChantHelper.canChantFromDiscardPile(this.x)) {
                ChantDiscardPile c = new ChantDiscardPile();
                c.magicNumber = c.baseMagicNumber = x;
                stanceChoices.add(c);
            }

        }
        else {
            ChantDrawPile c1 = new ChantDrawPile();
            c1.block = c1.baseBlock = x;
            c1.magicNumber = c1.baseMagicNumber = x;
            c1.upgrade();
            c1.upgraded = true;
            c1.applyPowers();
            ChantHand c2 = new ChantHand(giveCard);
            c2.magicNumber = c2.baseMagicNumber = x;
            c2.upgrade();
            c2.upgraded = true;
            ChantDiscardPile c3 = new ChantDiscardPile();
            c3.magicNumber = c3.baseMagicNumber = x;
            c3.upgrade();
            c3.upgraded = true;
            stanceChoices.add(c1);
            stanceChoices.add(c2);
            stanceChoices.add(c3);
        }
        if (!stanceChoices.isEmpty()) {
            this.addToTop(new ChooseOneAction(stanceChoices));
            for(AbstractCard c: AbstractDungeon.player.discardPile.group){
                if(c instanceof AbstractFrierenCard)
                    ((AbstractFrierenCard) c).triggerExhaustedCardsOnChant();
            }
        }
        this.isDone = true;
    }
}