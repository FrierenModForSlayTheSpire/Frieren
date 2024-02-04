package FrierenMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.MetallicizePower;

public class TongXiangAction extends AbstractGameAction {
    private static final float DUR = 0.25F;
    private final int magicNumber;

    public TongXiangAction(AbstractCreature target, AbstractCreature source, int magicNumber) {
        this.setValues(target, source, this.amount);
        this.actionType = ActionType.BLOCK;
        this.duration = 0.25F;
        this.magicNumber = magicNumber;
    }

    public void update() {
        if (!this.target.isDying && !this.target.isDead && this.duration == 0.25F && this.target.currentBlock > 0) {
            if(this.target.currentBlock > this.magicNumber){
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new MetallicizePower(AbstractDungeon.player, 3),3));
            }
            this.target.loseBlock();
        }

        this.tickDuration();
        this.isDone = true;
    }
}
