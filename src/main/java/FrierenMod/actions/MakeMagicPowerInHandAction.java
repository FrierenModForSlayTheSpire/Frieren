package FrierenMod.actions;

import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.powers.BanMagicGainPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MakeMagicPowerInHandAction extends AbstractGameAction {
    private final int amt;
    private static final String POWER_ID = BanMagicGainPower.POWER_ID;

    public MakeMagicPowerInHandAction(int amt) {
        this.amt = amt;
    }

    @Override
    public void update() {
        if(!AbstractDungeon.player.hasPower(POWER_ID)){
            this.addToBot(new MakeTempCardInHandAction(new MagicPower(),this.amt));
        }
        this.isDone = true;
    }
}
