package FrierenMod.actions;

import FrierenMod.cards.optionCards.ChantDiscardPile;
import FrierenMod.cards.optionCards.ChantDrawPile;
import FrierenMod.cards.optionCards.ChantHand;
import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import static FrierenMod.gameHelpers.HardCodedPowerHelper.CHANT_WITHOUT_MANA;

public class PreciseChantAction extends AbstractGameAction {

    @Override
    public void update() {
        int hand = ChantHelper.getMagicPowerNumInHand();
        int draw = ChantHelper.getMagicPowerNumInDrawPile();
        int discard = ChantHelper.getMagicPowerNumInDiscardPile();
        AbstractPlayer p = AbstractDungeon.player;
        ArrayList<AbstractCard> choices = new ArrayList<>();
        if(draw > 0){
            ChantDrawPile c = new ChantDrawPile();
            c.magicNumber = c.baseMagicNumber = draw;
            c.block = c.baseBlock = draw;
            c.applyPowers();
            choices.add(c);
        }
        if(hand > 0){
            ChantHand c = new ChantHand();
            c.magicNumber = c.baseMagicNumber = hand;
            choices.add(c);
        }
        if(discard > 0){
            ChantDiscardPile c = new ChantDiscardPile();
            c.magicNumber = c.baseMagicNumber = discard;
            choices.add(c);
        }
        if(!choices.isEmpty()){
            if(p.hasPower(CHANT_WITHOUT_MANA)){
                for(AbstractCard c:choices){
                    c.upgrade();
                    c.upgraded = true;
                }
            }
            this.addToTop(new ChooseOneAction(choices));
        }
        this.isDone = true;
    }
}
