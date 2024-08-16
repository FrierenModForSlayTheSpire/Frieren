package FrierenMod.powers;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FrushDownPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(FrushDownPower.class.getSimpleName());
    public static final int REST_AMT = 2;
    public int counts;

    public FrushDownPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
        this.counts = 2;
        this.updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(AbstractBaseCard.Enum.SYNCHRO)) {
            this.flash();
            this.counts--;
            if (this.counts <= 0) {
                int amt = this.amount;
                this.addToBot(new DrawCardAction(amt, new AbstractGameAction() {
                    @Override
                    public void update() {
                        for (int i = 0; i < amt; i++) {
                            AbstractCard c = AbstractDungeon.player.hand.group.get(i);
                            if (c != null) {
                                this.addToBot(new DiscardSpecificCardAction(c));
                            }
                        }
                        this.isDone = true;
                    }
                }));
                this.counts = REST_AMT;
            }
        } else {
            this.counts = REST_AMT;
        }
        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.counts = REST_AMT;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], counts, amount, amount);
    }
}