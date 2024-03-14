package FrierenMod.cards.tempCards;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;

public class CustomLegendaryMagic extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(CustomLegendaryMagic.class.getSimpleName());
    public String usedModifierText = this.rawDescription;
    public CustomLegendaryMagic() {
        super(ID, -1, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.isLegendaryMagic = true;
    }
    @Override
    public boolean canUpgrade(){
        return false;
    }

    @Override
    public void applyPowers() {
        this.rawDescription = this.usedModifierText;
        this.initializeDescription();
        super.applyPowers();
    }
}
