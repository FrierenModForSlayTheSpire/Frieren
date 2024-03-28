package FrierenMod.actions;

import FrierenMod.cards.tempCards.Mana;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static FrierenMod.gameHelpers.HardCodedPowerHelper.BAN_MANA_GAIN;

public class MakeManaInHandAction extends AbstractGameAction {
    private final int amt;

    public MakeManaInHandAction(int amt) {
        this.amt = amt;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if(!p.hasPower(BAN_MANA_GAIN)){
            this.addToBot(new MakeTempCardInHandAction(new Mana(),this.amt));
        }
        this.isDone = true;
    }
}
