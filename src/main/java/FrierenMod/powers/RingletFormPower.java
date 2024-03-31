package FrierenMod.powers;

import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;

public class RingletFormPower extends AbstractFrierenPower {
    public static final String POWER_ID = ModInformation.makeID(RingletFormPower.class.getSimpleName());

    public RingletFormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractMagicianCard && ((AbstractMagicianCard) card).isMana) {
            this.flash();
            this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player,this.amount));
        }
    }
    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }

}