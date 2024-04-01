package FrierenMod.powers;

import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import java.util.ArrayList;

public class SealPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(SealPower.class.getSimpleName());
    public final ArrayList<AbstractCard> cardsToSeal;
    public SealPower(AbstractCreature owner, ArrayList<AbstractCard> cardsToSeal) {
        super(POWER_ID, owner, PowerType.BUFF);
        this.cardsToSeal = cardsToSeal;
        this.updateDescription();
    }
    public void updateDescription() {
        int cardsAmt;
        try {
            cardsAmt = cardsToSeal.size();
        }catch (NullPointerException e){
            cardsAmt = 0;
        }
        this.description = String.format(descriptions[0], 5 - CombatHelper.getChantCardUsedThisTurn(),cardsAmt);
    }
    @Override
    public void afterChant() {
        this.flash();
        this.updateDescription();
        if(CombatHelper.getChantCardUsedThisTurn() >= 5){
            for(AbstractCard c:this.cardsToSeal){
                this.addToBot(new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
            }
            this.addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,POWER_ID));
        }
    }
    @Override
    public void onDrawOrDiscard() {
        this.updateDescription();
    }
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.updateDescription();
    }
}