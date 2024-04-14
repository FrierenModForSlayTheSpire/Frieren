package FrierenMod.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GainBlockAfterHandIsFullAction extends AbstractGameAction {
    public final AbstractPower po;

    public GainBlockAfterHandIsFullAction(AbstractPower po) {
        this.po = po;
    }

    @Override
    public void update() {
        if (po.owner instanceof AbstractPlayer && ((AbstractPlayer) po.owner).hand.size() >= BaseMod.MAX_HAND_SIZE) {
            po.flash();
            this.addToBot(new GainBlockAction(po.owner, po.amount));
        }
        this.isDone = true;
    }
}
