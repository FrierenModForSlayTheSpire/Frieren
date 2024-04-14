package FrierenMod.actions;

import FrierenMod.gameHelpers.CardPoolHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class FolkGrimoireAction extends AbstractGameAction {
    private final boolean upgraded;

    public FolkGrimoireAction(boolean upgraded) {
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        AbstractCard c1 = CardPoolHelper.getRandomCard(CardPoolHelper.PoolType.CHANT);
        AbstractCard c2 = CardPoolHelper.getRandomCard(CardPoolHelper.PoolType.LEGENDARY_SPELL);
        if (this.upgraded) {
            c1.setCostForTurn(c1.cost - 2);
            c2.setCostForTurn(c2.cost - 2);
        }
        this.addToBot(new MakeTempCardInHandAction(c1));
        this.addToBot(new MakeTempCardInHandAction(c2));
        this.isDone = true;
    }
}
