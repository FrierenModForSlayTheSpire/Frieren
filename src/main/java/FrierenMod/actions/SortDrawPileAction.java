package FrierenMod.actions;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Mana;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Comparator;

public class SortDrawPileAction extends AbstractGameAction {
    private static final Comparator<AbstractCard> BY_COST = Comparator.comparing((c) -> {
        if (c.hasTag(AbstractBaseCard.Enum.MANA))
            return -9;
        else
            return c.cost;
    });

    @Override
    public void update() {
        AbstractDungeon.player.drawPile.group.sort(BY_COST.reversed());
        this.isDone = true;
    }
}
