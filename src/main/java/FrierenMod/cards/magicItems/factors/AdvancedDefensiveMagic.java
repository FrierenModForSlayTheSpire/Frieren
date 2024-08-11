package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;

public class AdvancedDefensiveMagic extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(AdvancedDefensiveMagic.class.getSimpleName());

    public AdvancedDefensiveMagic() {
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
