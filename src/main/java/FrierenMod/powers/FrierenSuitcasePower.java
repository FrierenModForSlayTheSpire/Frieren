package FrierenMod.powers;

import FrierenMod.actions.GainBlockAfterHandIsFullAction;
import FrierenMod.utils.ModInformation;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class FrierenSuitcasePower extends AbstractBasePower {

    public static final String POWER_ID = ModInformation.makeID(FrierenSuitcasePower.class.getSimpleName());

    public FrierenSuitcasePower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
        updateDescription();
        BaseMod.MAX_HAND_SIZE = 13;
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        this.addToBot(new GainBlockAfterHandIsFullAction(this));
    }

    public void onVictory() {
        BaseMod.MAX_HAND_SIZE = 10;
    }

    public void onRemove() {
        BaseMod.MAX_HAND_SIZE = 10;
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}
