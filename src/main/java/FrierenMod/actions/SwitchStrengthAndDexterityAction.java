package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class SwitchStrengthAndDexterityAction extends AbstractGameAction {
    private final boolean hasNextAction;
    private final AbstractGameAction nextAction;
    public SwitchStrengthAndDexterityAction(){
        this.hasNextAction = false;
        this.nextAction = null;
    }
    public SwitchStrengthAndDexterityAction(AbstractGameAction nextAction){
        this.hasNextAction = true;
        this.nextAction = nextAction;
    }
    @Override
    public void update() {
        int strengthAmounts = 0;
        int dexterityAmounts = 0;
        AbstractPlayer p = AbstractDungeon.player;
        for(AbstractPower po: p.powers){
            if(po.ID.matches("Strength")){
                strengthAmounts = po.amount;
            }
            if(po.ID.matches("Dexterity")){
                dexterityAmounts = po.amount;
            }
            if(strengthAmounts != 0 && dexterityAmounts != 0){
                break;
            }
        }
        if(strengthAmounts == 0 && dexterityAmounts == 0){
            this.isDone = true;
            return;
        }
        if(strengthAmounts != 0 && dexterityAmounts != 0){
            this.addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,strengthAmounts * (-1)),strengthAmounts * (-1)));
            this.addToBot(new ApplyPowerAction(p,p,new DexterityPower(p,dexterityAmounts * (-1)),dexterityAmounts * (-1)));
            this.addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,dexterityAmounts),dexterityAmounts));
            this.addToBot(new ApplyPowerAction(p,p,new DexterityPower(p,strengthAmounts),strengthAmounts));
        }
        else if(strengthAmounts == 0){
            this.addToBot(new ApplyPowerAction(p,p,new DexterityPower(p,dexterityAmounts * (-1)),dexterityAmounts * (-1)));
            this.addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,dexterityAmounts),dexterityAmounts));
        }else{
            this.addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,strengthAmounts * (-1)),strengthAmounts * (-1)));
            this.addToBot(new ApplyPowerAction(p,p,new DexterityPower(p,strengthAmounts),strengthAmounts));
        }
        if(this.hasNextAction){
            this.addToBot(nextAction);
        }
        this.isDone = true;
    }
}
