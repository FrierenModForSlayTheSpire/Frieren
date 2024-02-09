package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public abstract class AbstractFrierenAction extends AbstractGameAction {
    public boolean isMagicPowerAction;
    public AbstractFrierenAction(){
        this.isMagicPowerAction = false;
    }
    @Override
    public void update() {

    }
}
