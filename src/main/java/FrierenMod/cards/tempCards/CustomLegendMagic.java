package FrierenMod.cards.tempCards;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;

public class CustomLegendMagic extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(CustomLegendMagic.class.getSimpleName());
    public CustomLegendMagic() {
        super(ID, -1, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.isLegendMagicCard = true;
    }
    @Override
    public boolean canUpgrade(){
        return false;
    }
}
