package FrierenMod.actions;

import FrierenMod.gameHelpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class ApexMagicAction extends AbstractGameAction {

    private final int magicNumber;
    public ApexMagicAction(int magicNumber){
        this.magicNumber = magicNumber;
    }
    @Override
    public void update() {
        if (ChantHelper.getManaNumInDrawPile() == 4 && ChantHelper.getManaNumInHand() == 4 && ChantHelper.getManaNumInDiscardPile() == 4){
            this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(999, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
        }else {
            this.addToBot(new DrawCardAction(this.magicNumber));
        }
        this.isDone = true;
    }
}
