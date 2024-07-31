package FrierenMod.relics;

import FrierenMod.actions.MakeManaInDiscardAction;
import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class HolyEmblem extends AbstractBaseRelic {
    public static final String ID = ModInformation.makeID(HolyEmblem.class.getSimpleName());
    public HolyEmblem() {
        super(ID, RelicTier.STARTER);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new HolyEmblem();
    }
    @Override
    public void atTurnStartPostDraw() {
        this.flash();
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new RelicAboveCreatureAction(p, this));
        addToBot(new MakeManaInDrawPileAction(1));
        addToBot(new MakeManaInDiscardAction(1));
    }
}
