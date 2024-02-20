package FrierenMod.powers;

import FrierenMod.cards.tempCards.HideMagic;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class TrickPower extends AbstractFrierenPower {
    public static final String POWER_ID = ModInformation.makeID(TrickPower.class.getSimpleName());
    public TrickPower(AbstractCreature owner) {
        super(POWER_ID, owner, PowerType.BUFF);
    }

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            for(AbstractCard c:AbstractDungeon.player.hand.group){
                if(c.cardID.matches(HideMagic.ID) ){
                    return;
                }
            }
            this.addToBot(new MakeTempCardInHandAction(new HideMagic(),1));
        }
    }
    public void updateDescription() {
        this.description = descriptions[0];
    }
}