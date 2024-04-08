package FrierenMod.actions;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.powers.AbstractBasePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class AfterChantFinishedAction extends AbstractGameAction {
    private AbstractBaseCard c;
    private AbstractBasePower po;
    public AfterChantFinishedAction(AbstractBaseCard c){
        this.c = c;
    }
    public AfterChantFinishedAction(AbstractBasePower po){
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
