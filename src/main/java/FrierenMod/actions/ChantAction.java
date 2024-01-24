package FrierenMod.actions;

import FrierenMod.cards.optionCards.ChantFromDiscardPile;
import FrierenMod.cards.optionCards.ChantFromDrawPile;
import FrierenMod.cards.optionCards.ChantFromHand;
import FrierenMod.helpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import java.util.ArrayList;

public class ChantAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int x;
    private ChantHelper helper;

    public ChantAction(AbstractPlayer p, int x) {
        this.p = p;
        this.x = x;
    }

    @Override
    public void update() {
        helper = new ChantHelper();
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        if (helper.canChantFromDrawPile(this.x)) {
            ChantFromDrawPile c = new ChantFromDrawPile();
            c.magicNumber = c.baseMagicNumber = x;
            stanceChoices.add(c);
        }
        if (helper.canChantFromHand(this.x)) {
            ChantFromHand c = new ChantFromHand();
            c.magicNumber = c.baseMagicNumber = x;
            stanceChoices.add(c);
        }
        if (helper.canChantFromDiscardPile(this.x)) {
            ChantFromDiscardPile c = new ChantFromDiscardPile();
            c.magicNumber = c.baseMagicNumber = x;
            stanceChoices.add(c);
        }
        this.addToBot(new ChooseOneAction(stanceChoices));
        this.isDone = true;
    }
}

