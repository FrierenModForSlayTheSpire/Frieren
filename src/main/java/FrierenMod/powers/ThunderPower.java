package FrierenMod.powers;

import FrierenMod.cardMods.ManaMod;
import FrierenMod.cards.AbstractBaseCard;
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
    public void atStartOfTurnPostDraw() {
        upgradeAllMagicPower();
    }
    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        upgradeAllMagicPower();
    }
    public void updateDescription() {
        this.description = descriptions[0];
    }
    private void upgradeAllMagicPowerInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if(c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isMana && !((AbstractBaseCard) c).isLimitedOverMana){
                if (((AbstractBaseCard) c).isAccelMana) {
                    if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                        c.superFlash();
                    }
                    CardModifierManager.addModifier(c, new ManaMod(4));
                    c.applyPowers();
                } else{
                    if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                        c.superFlash();
                    }
                    CardModifierManager.addModifier(c, new ManaMod(3));
                    c.applyPowers();
                }
            }
        }
    }
    public void upgradeAllMagicPower(){
        upgradeAllMagicPowerInGroup(p.drawPile);
        upgradeAllMagicPowerInGroup(p.hand);
        upgradeAllMagicPowerInGroup(p.discardPile);
        upgradeAllMagicPowerInGroup(p.exhaustPile);
    }
}