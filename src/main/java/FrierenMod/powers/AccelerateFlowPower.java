package FrierenMod.powers;

import FrierenMod.cardMods.ManaMod;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AccelerateFlowPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(AccelerateFlowPower.class.getSimpleName());
    private final AbstractPlayer p = AbstractDungeon.player;
    public AccelerateFlowPower(AbstractCreature owner) {
        super(POWER_ID, owner, PowerType.BUFF);
        this.updateDescription();
    }
    @Override
    public void onInitialApplication() {
        upgradeAllMagicPower();
    }
    @Override
    public void onDrawOrDiscard() {
        upgradeAllMagicPower();
    }
    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        upgradeAllMagicPower();
    }
    public void atEndOfTurn(boolean isPlayer) {
        degradeMagicPower();
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
    public void updateDescription() {
        this.description = descriptions[0];
    }
    private void upgradeAllMagicPowerInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if(c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isMana && !((AbstractBaseCard) c).isAccelMana){
                if (((AbstractBaseCard) c).isLimitedOverMana) {
                    if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                        c.superFlash();
                    }
                    CardModifierManager.addModifier(c, new ManaMod(4));
                    c.applyPowers();
                } else{
                    if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                        c.superFlash();
                    }
                    CardModifierManager.addModifier(c, new ManaMod(2));
                    c.applyPowers();
                }
            }
        }
    }
    private void upgradeAllMagicPower(){
        upgradeAllMagicPowerInGroup(p.drawPile);
        upgradeAllMagicPowerInGroup(p.hand);
        upgradeAllMagicPowerInGroup(p.discardPile);
        upgradeAllMagicPowerInGroup(p.exhaustPile);
    }
    private void degradeMagicPowerInGroup(CardGroup cardGroup){
        for (AbstractCard c : cardGroup.group) {
            if(c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isMana && ((AbstractBaseCard) c).isAccelMana){
                if (((AbstractBaseCard) c).isLimitedOverMana) {
                    CardModifierManager.addModifier(c, new ManaMod(3));
                }else {
                    CardModifierManager.addModifier(c, new ManaMod(1));
                }
            }
        }
    }
    private void degradeMagicPower(){
        degradeMagicPowerInGroup(p.drawPile);
        degradeMagicPowerInGroup(p.hand);
        degradeMagicPowerInGroup(p.discardPile);
        degradeMagicPowerInGroup(p.exhaustPile);
    }
}