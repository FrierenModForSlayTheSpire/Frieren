package FrierenMod.actions;

import FrierenMod.cards.tempCards.MagicPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static FrierenMod.gameHelpers.HardCodedPowerHelper.BAN_MAGIC_GAIN;

public class MakeMagicPowerInHandAction extends AbstractGameAction {
    private final int amt;

    public MakeMagicPowerInHandAction(int amt) {
        this.amt = amt;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if(!p.hasPower(BAN_MAGIC_GAIN)){
            this.addToBot(new MakeTempCardInHandAction(new MagicPower(),this.amt));
        }
        this.isDone = true;
    }
}
