package FrierenMod.powers;

import FrierenMod.cards.tempCards.ManaConcealment;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class TrickPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(TrickPower.class.getSimpleName());
    public TrickPower(AbstractCreature owner) {
        super(POWER_ID, owner, PowerType.BUFF);
        this.updateDescription();
    }

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            this.addToBot(new MakeTempCardInHandAction(new ManaConcealment(),1));
        }
    }
    public void updateDescription() {
        this.description = descriptions[0];
    }
}