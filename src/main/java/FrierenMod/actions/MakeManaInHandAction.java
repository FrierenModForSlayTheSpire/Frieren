package FrierenMod.actions;

import FrierenMod.cards.canAutoAdd.tempCards.Mana;
import FrierenMod.powers.BanManaGainPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MakeManaInHandAction extends AbstractGameAction {
    private final int amt;

    public MakeManaInHandAction(int amt) {
        this.amt = amt;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if (!p.hasPower(BanManaGainPower.POWER_ID)) {
            this.addToBot(new MakeTempCardInHandAction(new Mana(), this.amt));
        }
        this.isDone = true;
    }
}
