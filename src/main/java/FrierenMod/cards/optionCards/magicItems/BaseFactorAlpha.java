package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;

public class BaseFactorAlpha extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BaseFactorAlpha.class.getSimpleName());

    public BaseFactorAlpha() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.BASIC;
    }

    @Override
    public void takeEffect() {
        for (int i = 0; i < 2; i++) {
            this.addToBot(new GainBlockAction(p, p, this.secondMagicNumber));
        }
    }
}
