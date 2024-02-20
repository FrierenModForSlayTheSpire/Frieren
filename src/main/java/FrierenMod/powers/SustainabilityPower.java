package FrierenMod.powers;

import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SustainabilityPower extends AbstractFrierenPower {
    public static final String POWER_ID = ModInformation.makeID(SustainabilityPower.class.getSimpleName());

    public SustainabilityPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
    }

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            this.addToBot(new MakeTempCardInDiscardAction(new MagicPower(),this.amount * 2));
        }
    }
    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount * 2);
    }

}