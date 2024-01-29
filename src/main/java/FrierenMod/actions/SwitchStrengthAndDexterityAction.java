package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class SwitchStrengthAndDexterityAction extends AbstractGameAction {
    @Override
    public void update() {
        int strengthAmounts = 0;
        int dexterityAmounts = 0;
        for(AbstractPower po: AbstractDungeon.player.powers){
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
        if(strengthAmounts != 0 || dexterityAmounts != 0){
            AbstractDungeon.player.powers.clear();
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new StrengthPower(AbstractDungeon.player,dexterityAmounts),dexterityAmounts));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new DexterityPower(AbstractDungeon.player,strengthAmounts),strengthAmounts));
        }
        this.isDone = true;
    }
}
