package FrierenMod.relics;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.ModInformation;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

@AutoAdd.Ignore
public class OLDHairAccessory extends AbstractBaseRelic {
    public static final String ID = ModInformation.makeID(OLDHairAccessory.class.getSimpleName());
    public OLDHairAccessory() {
        super(ID, RelicTier.RARE);
    }
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new OLDHairAccessory();
    }

    public void atBattleStart() {
        this.counter = 0;
    }
    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.hasTag(AbstractBaseCard.Enum.SYNCHRO)){
            this.counter++;
            if (this.counter >= 4) {
                this.counter = 0;
                flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new DrawCardAction(AbstractDungeon.player, 1));
            }
        }
    }

    public void onVictory() {
        this.counter = -1;
    }
}