package FrierenMod.relics;

import FrierenMod.gameHelpers.SlotBgHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class MercuryPudding extends AbstractBaseRelic {
    public static final String ID = ModInformation.makeID(MercuryPudding.class.getSimpleName());
    public MercuryPudding() {
        super(ID, RelicTier.UNCOMMON);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
    @Override
    public void setCounter(int setCounter) {
        if (this.counter != -2) {
            usedUp();
        }
        this.counter = setCounter;
    }

    @Override
    public void usedUp() {
        super.usedUp();
        SlotBgHelper.unlockANewSlot("4011");
    }

    public void onVictory() {
        addToTop((AbstractGameAction) new RelicAboveCreatureAction((AbstractCreature) AbstractDungeon.player, this));
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth <= 10) {
            if (this.counter == -2) return ;
                flash();
                int healAmt = p.maxHealth / 2;
                AbstractDungeon.player.heal(healAmt, true);
                this.setCounter(-2);
        }
    }
    public AbstractRelic makeCopy() {
        return new MercuryPudding();
    }
}