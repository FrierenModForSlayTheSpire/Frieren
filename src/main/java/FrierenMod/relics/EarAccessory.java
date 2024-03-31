package FrierenMod.relics;

import FrierenMod.cards.canAutoAdd.tempCards.Mana;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class EarAccessory extends AbstractBaseRelic {
    public static final String ID = ModInformation.makeID(EarAccessory.class.getSimpleName());
    public EarAccessory() {
        super(ID, RelicTier.COMMON);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new EarAccessory();
    }
    @Override
    public void atBattleStart() {
        flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new MakeTempCardInHandAction(new Mana(),2));
    }
}

