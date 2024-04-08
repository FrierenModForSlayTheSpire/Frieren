package FrierenMod.actions;

import FrierenMod.cards.canAutoAdd.tempCards.SecondDefensiveMagic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DefensiveMagicAction extends AbstractGameAction {
    private final int chantX;
    private final int block;
    private final boolean upgraded;
    private final AbstractCard cardsToPreview;
    public DefensiveMagicAction(int chantX, int block, boolean upgraded, AbstractCard cardsToPreview){
        this.chantX = chantX;
        this.block = block;
        this.upgraded = upgraded;
        this.cardsToPreview = cardsToPreview;
    }
    @Override
    public void update() {
        this.addToBot(new ChantAction(this.chantX));
        this.addToBot(new GainBlockAction(AbstractDungeon.player,this.block));
        if(this.upgraded){
            AbstractCard c = new SecondDefensiveMagic();
            c.upgrade();
            this.addToBot(new MakeTempCardInHandAction(c));
        }
        else {
            this.addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeCopy()));
        }
        this.isDone = true;
    }
}
