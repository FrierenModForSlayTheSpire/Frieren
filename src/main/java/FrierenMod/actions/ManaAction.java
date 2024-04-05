package FrierenMod.actions;

import FrierenMod.cards.AbstractFrierenCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ManaAction extends AbstractGameAction{
    private final int type;
    private AbstractCard c;
    public ManaAction(int type){
        this.type = type;
    }
    public ManaAction(AbstractCard c, int type){
        this(type);
        this.c = c;
    }
    @Override
    public void update() {
        switch (type){
            case 1:
                this.addToBot(new DrawCardAction(1));
                break;
            case 2:
                this.addToBot(new DrawCardAction(2));
                break;
            case 3:
                this.addToBot(new AttackDamageRandomEnemyAction(c, AbstractGameAction.AttackEffect.LIGHTNING));
                break;
            case 4:
                this.addToBot(new AttackDamageRandomEnemyAction(c, AbstractGameAction.AttackEffect.LIGHTNING));
                this.addToBot(new DrawCardAction(2));
                break;
            default:
                break;
        }
        for(AbstractCard c: AbstractDungeon.player.discardPile.group)
            if(c instanceof AbstractFrierenCard)
                ((AbstractFrierenCard) c).afterSynchro();
        this.isDone = true;
    }
}
