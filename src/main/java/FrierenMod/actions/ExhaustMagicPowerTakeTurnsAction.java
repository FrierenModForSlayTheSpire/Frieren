package FrierenMod.actions;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.helpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExhaustMagicPowerTakeTurnsAction extends AbstractGameAction {
    private final int requiredAmt;

    public ExhaustMagicPowerTakeTurnsAction(int requiredAmt) {
        this.requiredAmt = requiredAmt;
    }

    @Override
    public void update() {
        ChantHelper helper = new ChantHelper();
        if(helper.getAllMagicPowerNum() >= requiredAmt){
            int draw = helper.getMagicPowerNumInDrawPile();
            int hand = helper.getMagicPowerNumInHand();
            int discard = helper.getMagicPowerNumInDiscardPile();
            AbstractPlayer p = AbstractDungeon.player;
            if(draw >= requiredAmt){
                this.addToBot(new ExhaustMagicPowerInDrawPileAction(requiredAmt));
            }
            else {
                this.addToBot(new ExhaustMagicPowerInDrawPileAction(draw));
                int diff = requiredAmt - draw;
                if(diff <= hand){
                    this.addToBot(new ExhaustMagicPowerInHandAction(diff));
                }else {
                    this.addToBot(new ExhaustMagicPowerInHandAction(hand));
                    int diff2 = requiredAmt - hand - draw;
                    this.addToBot(new ExhaustMagicPowerInDiscardPileAction(diff2));
                }
            }
        }
        this.isDone = true;
    }
}
