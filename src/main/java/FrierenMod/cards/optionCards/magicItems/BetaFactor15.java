package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;

public class BetaFactor15 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor15.class.getSimpleName());

    public BetaFactor15() {
        super(ID);
        loadRarity(MagicItemRarity.COMMON);
    }

    @Override
    public void takeEffect() {
        for (int i = 0; i < 2; i++) {
            this.addToBot(new GainBlockAction(p, p, this.block));
        }
    }
}
