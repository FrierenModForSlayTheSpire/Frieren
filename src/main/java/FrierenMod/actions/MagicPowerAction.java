package FrierenMod.actions;

import FrierenMod.cards.tempCards.MagicPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class MagicPowerAction extends AbstractFrierenAction{
    private final int type;
    private AbstractCard c;
    public MagicPowerAction(int type){
        this.type = type;
        this.isMagicPowerAction = true;
    }
    public MagicPowerAction(AbstractCard c,int type){
        this.c = c;
        this.type = type;
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
        this.isDone = true;
    }
}
