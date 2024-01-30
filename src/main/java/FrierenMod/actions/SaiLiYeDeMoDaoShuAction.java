package FrierenMod.actions;

import FrierenMod.cards.tempCards.CustomLegendMagic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;

public class SaiLiYeDeMoDaoShuAction extends AbstractGameAction {
    @Override
    public void update() {
        CustomLegendMagic c = new CustomLegendMagic();
//        this.addToBot(new CustomCardCostAction(new MakeTempCardInHandAction(c),c));
        this.addToBot(new CustomCardCostAction(new CustomCardLayer2Action(new CustomCardLayer3Action(new CustomCardLayer4Action(new MakeTempCardInHandAction(c),c),c),c),c));
        this.isDone = true;
    }
}
