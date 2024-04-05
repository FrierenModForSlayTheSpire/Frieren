package FrierenMod.actions;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.powers.AbstractFrierenPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class AfterChantFinishedAction extends AbstractGameAction {
    private AbstractFrierenCard c;
    private AbstractFrierenPower po;
    public AfterChantFinishedAction(AbstractFrierenCard c){
        this.c = c;
    }
    public AfterChantFinishedAction(AbstractFrierenPower po){
        this.po = po;
    }
    @Override
    public void update() {
        if(c != null)
            c.afterChantFinished();
        if(po != null)
            po.afterChantFinished();
        this.isDone = true;
    }
}
