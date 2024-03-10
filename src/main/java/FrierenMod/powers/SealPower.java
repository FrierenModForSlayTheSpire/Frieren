package FrierenMod.powers;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.white.HellFireSummoning;
import FrierenMod.gameHelpers.LegendMagicHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SealPower extends AbstractFrierenPower {
    public static final String POWER_ID = ModInformation.makeID(SealPower.class.getSimpleName());
    public SealPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, PowerType.BUFF);
        this.updateDescription();
    }
    public void updateDescription() {
        this.description = String.format(descriptions[0], 5 - LegendMagicHelper.getChantCardUsedThisTurn());
    }
    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        if(usedCard instanceof AbstractFrierenCard && ((AbstractFrierenCard) usedCard).isChantCard){
            this.flash();
            this.updateDescription();
            if(LegendMagicHelper.getChantCardUsedThisTurn() >= 5){
                for (int i = 0; i < this.amount; i++) {
                    this.addToBot(new MakeTempCardInHandAction(new HellFireSummoning()));
                    AbstractPlayer p = AbstractDungeon.player;
                }
                this.addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,POWER_ID));
            }
        }
    }
}