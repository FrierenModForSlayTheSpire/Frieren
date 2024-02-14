package FrierenMod.relics;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class MercuryPudding extends AbstractFrierenRelic {
    public static final String ID = ModInformation.makeID(MercuryPudding.class.getSimpleName());
    public MercuryPudding() {
        super(ID, RelicTier.UNCOMMON);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
    @Override
    public void setCounter(int setCounter) {
        if (setCounter == -2) {
            usedUp();
            this.counter = -2;
        }
    }

    public void onVictory() {
        flash();
        addToTop((AbstractGameAction) new RelicAboveCreatureAction((AbstractCreature) AbstractDungeon.player, this));
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth <= 10) {
            int healAmt =p.maxHealth/2;
            AbstractDungeon.player.heal(healAmt, true);
            this.setCounter(-2);
        }
    }
    public AbstractRelic makeCopy() {
        return new MercuryPudding();
    }
}