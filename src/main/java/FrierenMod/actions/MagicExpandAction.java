package FrierenMod.actions;

import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.cards.white.MagicExpand;
import FrierenMod.powers.ExpandPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MagicExpandAction extends AbstractGameAction {
    private final int magicPowerNum;

    public MagicExpandAction(int magicPowerNum) {
        this.magicPowerNum = magicPowerNum;
    }
    @Override
    public void update() {
        this.addToBot(new MakeTempCardInHandAction(new MagicPower(),this.magicPowerNum));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new ExpandPower(AbstractDungeon.player)));
        this.isDone = true;
    }
}
