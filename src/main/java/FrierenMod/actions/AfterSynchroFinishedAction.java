package FrierenMod.actions;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.powers.AbstractBasePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class AfterSynchroFinishedAction extends AbstractGameAction {
    private AbstractBaseCard c;
    private AbstractBasePower po;
    public AfterSynchroFinishedAction(AbstractBaseCard c){
        this.c = c;
    }
    public AfterSynchroFinishedAction(AbstractBasePower po){
        this.po = po;
    }
    @Override
    public void update() {
        if(c != null)
            c.afterSynchroFinished();;
        if(po != null)
            po.afterSynchroFinished();
        this.isDone = true;
    }
}