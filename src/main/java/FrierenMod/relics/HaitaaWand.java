package FrierenMod.relics;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class HaitaaWand extends AbstractBaseRelic{
    public static final String ID = ModInformation.makeID(HaitaaWand.class.getSimpleName());
    public HaitaaWand() {
        super(ID, RelicTier.STARTER);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new HaitaaWand();
    }
    @Override
    public void onPlayerEndTurn() {
        this.flash();
    }
}
