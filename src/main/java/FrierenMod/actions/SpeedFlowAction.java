package FrierenMod.actions;

import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.powers.SpeedFlowPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SpeedFlowAction extends AbstractGameAction {
    private final int magicPowerNum;

    public SpeedFlowAction(int magicPowerNum) {
        this.magicPowerNum = magicPowerNum;
    }
    @Override
    public void update() {
        this.addToBot(new MakeTempCardInHandAction(new MagicPower(),this.magicPowerNum));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new SpeedFlowPower(AbstractDungeon.player)));
        this.isDone = true;
    }
}
