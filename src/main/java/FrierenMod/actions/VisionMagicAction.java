package FrierenMod.actions;

import FrierenMod.helpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class VisionMagicAction extends AbstractGameAction {

    private final int magicNumber;
    public VisionMagicAction(int magicNumber){
        this.magicNumber = magicNumber;
    }
    @Override
    public void update() {
        ChantHelper helper = new ChantHelper();
        if (helper.getMagicPowerNumInDrawPile() == 6 && helper.getMagicPowerNumInHand() == 6 && helper.getMagicPowerNumInDiscardPile() == 6){
            this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(999, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
        }else {
            this.addToBot(new DrawCardAction(this.magicNumber));
        }
        this.isDone = true;
    }
}