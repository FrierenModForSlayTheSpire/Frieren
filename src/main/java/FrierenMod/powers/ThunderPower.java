package FrierenMod.powers;

import FrierenMod.cardMods.ManaMod;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ThunderPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(ThunderPower.class.getSimpleName());
    private final AbstractPlayer p = AbstractDungeon.player;

    public ThunderPower(AbstractCreature owner) {
        super(POWER_ID, owner, PowerType.BUFF);
        this.updateDescription();
    }
    @Override
    public void onInitialApplication() {
        upgradeMana();
    }
    @Override
    public void onDrawOrDiscard() {
        upgradeMana();
    }
    @Override
    public void atStartOfTurnPostDraw() {
        upgradeMana();
    }
    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        upgradeMana();
    }
    public void updateDescription() {
        this.description = descriptions[0];
    }
    private void upgradeManaInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if(c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isMana && !((AbstractBaseCard) c).isLimitedOverMana){
                if (((AbstractBaseCard) c).isAccelMana) {
                    if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                        c.superFlash();
                    }
                    CardModifierManager.addModifier(c, new ManaMod(Mana.Type.LIMITED_OVER_ACCEL));
                    c.applyPowers();
                } else{
                    if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                        c.superFlash();
                    }
                    CardModifierManager.addModifier(c, new ManaMod(Mana.Type.LIMITED_OVER));
                    c.applyPowers();
                }
            }
        }
    }
    public void upgradeMana(){
        upgradeManaInGroup(p.drawPile);
        upgradeManaInGroup(p.hand);
        upgradeManaInGroup(p.discardPile);
        upgradeManaInGroup(p.exhaustPile);
    }
}