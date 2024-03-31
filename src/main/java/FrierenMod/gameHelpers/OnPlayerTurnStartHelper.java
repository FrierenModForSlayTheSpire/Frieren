package FrierenMod.gameHelpers;

import FrierenMod.cards.AbstractBaseCard;
import basemod.interfaces.OnPlayerTurnStartSubscriber;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class OnPlayerTurnStartHelper implements OnPlayerTurnStartSubscriber {
    @Override
    public void receiveOnPlayerTurnStart() {
        try {
            ResetCardCost();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    private void ResetBackFireCardCostInCardGroup(CardGroup group) throws InstantiationException, IllegalAccessException {
        for(AbstractCard c:group.group){
            if(c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isCostResetCard){
                Class<? extends AbstractBaseCard> cardClass = (Class<? extends AbstractBaseCard>) c.getClass();
                c.cost = cardClass.newInstance().cost;
                c.costForTurn = c.cost;
                c.isCostModified = false;
            }
        }
    }
    private void ResetCardCost() throws InstantiationException, IllegalAccessException {
        AbstractPlayer p = AbstractDungeon.player;
        ResetBackFireCardCostInCardGroup(p.hand);
        ResetBackFireCardCostInCardGroup(p.discardPile);
        ResetBackFireCardCostInCardGroup(p.drawPile);
        ResetBackFireCardCostInCardGroup(p.exhaustPile);
    }
}
