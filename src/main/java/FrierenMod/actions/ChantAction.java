package FrierenMod.actions;

import FrierenMod.cards.optionCards.ChantFromDiscardPile;
import FrierenMod.cards.optionCards.ChantFromDrawPile;
import FrierenMod.cards.optionCards.ChantFromHand;
import FrierenMod.helpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class ChantAction extends AbstractGameAction {
    private final int x;
    public ChantAction(int x) {
        this.x = x;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            ChantHelper helper = new ChantHelper();
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            if (helper.canChantFromDrawPile(this.x)) {
                ChantFromDrawPile c = new ChantFromDrawPile();
                c.magicNumber = c.baseMagicNumber = x;
                int counts = 0;
                for (AbstractPower po : AbstractDungeon.player.powers){
                    if(po.ID.matches("Dexterity")){
                        counts += po.amount;
                        break;
                    }
                }
                c.baseBlock = c.block = c.magicNumber + counts;
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
            if (!stanceChoices.isEmpty()) {
                this.addToTop(new ChooseOneAction(stanceChoices));
            }
        }

        this.tickDuration();
        this.isDone = true;
    }
}