package FrierenMod.actions;

import FrierenMod.cards.tempCards.CustomLegendarySpell;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;

public class SerieGrimoireAction extends AbstractGameAction {
    private final boolean upgraded;

    public SerieGrimoireAction(boolean upgraded) {
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        CustomLegendarySpell c = new CustomLegendarySpell();
        this.addToBot(new CustomCardCostAction(new CustomCardLayer2Action(new CustomCardLayer3Action(new CustomCardLayer4Action(new MakeTempCardInHandAction(c),c,upgraded),c,upgraded),c,upgraded),c));
        this.isDone = true;
    }
}
