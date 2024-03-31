package FrierenMod.actions;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.powers.AbstractBasePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class ChantFromCardGroupAction extends AbstractGameAction {
    public AbstractGameAction[] nextAction;
    public boolean haveNotTriggered;
    public void triggerPowers(){
        if(haveNotTriggered)
            for(AbstractPower po: AbstractDungeon.player.powers)
                if(po instanceof AbstractBasePower)
                    ((AbstractBasePower) po).afterChant();
    }
    public void triggerCards(){
        AbstractPlayer p = AbstractDungeon.player;
        triggerCardsInCardGroup(p.drawPile);
        triggerCardsInCardGroup(p.hand);
        triggerCardsInCardGroup(p.discardPile);
    }
    private void triggerCardsInCardGroup(CardGroup group){
        if(haveNotTriggered)
            for(AbstractCard c:group.group)
                if(c instanceof AbstractBaseCard)
                    ((AbstractBaseCard) c).afterChant();
    }
    public void addNextAction(){
        if(haveNotTriggered)
            if(nextAction != null)
                for(AbstractGameAction action:nextAction)
                    this.addToBot(action);
    }
}
