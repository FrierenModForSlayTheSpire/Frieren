package FrierenMod.actions;

import FrierenMod.helpers.ChantHelper;
import FrierenMod.powers.ThunderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ThunderMagicAction extends AbstractGameAction {
    @Override
    public void update() {
        int draw = new ChantHelper().getMagicPowerNumInDrawPile();
        int discard = new ChantHelper().getMagicPowerNumInDiscardPile();
        if(draw > 0){
            this.addToBot(new DrawMagicAction(draw));
        }
        if(discard > 0){
            this.addToBot(new DrawMagicFromDiscardPileAction(discard));
        }
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new ApplyPowerAction(p,p,new ThunderPower(p)));
        this.isDone = true;
    }
}
