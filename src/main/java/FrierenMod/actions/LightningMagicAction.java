package FrierenMod.actions;

import FrierenMod.gameHelpers.ChantHelper;
import FrierenMod.powers.ThunderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class LightningMagicAction extends AbstractGameAction {
    @Override
    public void update() {
        int draw = ChantHelper.getManaNumInDrawPile();
        int discard = ChantHelper.getManaNumInDiscardPile();
        if(draw > 0){
            this.addToBot(new DrawMagicAction(draw));
        }
        if(discard > 0){
            this.addToBot(new DrawManaFromDiscardPileAction(discard));
        }
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new ApplyPowerAction(p,p,new ThunderPower(p)));
        this.isDone = true;
    }
}
