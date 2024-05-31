package FrierenMod.actions;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Mana;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class ManaAction extends AbstractGameAction {
    private final Mana.Type type;
    private AbstractCard c;
    private ArrayList<AbstractGameAction> actionsToRecover;

    public ManaAction(Mana.Type type) {
        this.type = type;
    }

    public ManaAction(AbstractCard c, Mana.Type type) {
        this(type);
        this.c = c;
    }
    public ManaAction(AbstractCard c, Mana.Type type, ArrayList<AbstractGameAction> actionsToRecover) {
        this(type);
        this.c = c;
        this.actionsToRecover = actionsToRecover;
    }

    @Override
    public void update() {
        switch (type) {
            case NORMAL:
                this.addToBot(new DrawCardAction(1));
                break;
            case ACCEL:
                this.addToBot(new DrawCardAction(2));
                break;
            case LIMITED_OVER:
                this.addToBot(new AttackDamageRandomEnemyAction(c, AbstractGameAction.AttackEffect.LIGHTNING));
                break;
            case LIMITED_OVER_ACCEL:
                this.addToBot(new AttackDamageRandomEnemyAction(c, AbstractGameAction.AttackEffect.LIGHTNING));
                this.addToBot(new DrawCardAction(2));
                break;
            default:
                break;
        }
        if(actionsToRecover != null && !actionsToRecover.isEmpty()){
            for(AbstractGameAction action : actionsToRecover){
                this.addToBot(action);
            }
        }
        this.triggerCardsInGroup(AbstractDungeon.player.discardPile);
        this.isDone = true;
    }

    private void triggerCardsInGroup(CardGroup group) {
        for (AbstractCard c : group.group)
            if (c instanceof AbstractBaseCard) {
                ((AbstractBaseCard) c).afterSynchroFinished();
            }
    }
}
