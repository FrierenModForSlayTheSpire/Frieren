package FrierenMod.relics;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class UbelStaff extends AbstractBaseRelic {
    public static final String ID = ModInformation.makeID(UbelStaff.class.getSimpleName());
    private static final int TRIGGER_VALUE = 6;

    public UbelStaff() {
        super(ID, RelicTier.UNCOMMON);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractBaseCard && ((AbstractBaseCard) card).isChantCard) {
            this.counter++;
            if (this.counter == TRIGGER_VALUE) {
                this.counter = 0;
                flash();
                this.pulse = false;
            } else if (this.counter == TRIGGER_VALUE - 1) {
                beginPulse();
                this.pulse = true;
                AbstractDungeon.player.hand.refreshHandLayout();
                addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                addToBot(new GainEnergyAction(1));
            }
        }
    }

    public void atBattleStart() {
        if (this.counter == TRIGGER_VALUE) {
            beginPulse();
            this.pulse = true;
        }
    }
}

