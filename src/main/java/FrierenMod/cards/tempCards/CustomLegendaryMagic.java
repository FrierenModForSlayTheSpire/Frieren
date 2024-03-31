package FrierenMod.cards.tempCards;

import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;

public class CustomLegendaryMagic extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(CustomLegendaryMagic.class.getSimpleName());
    public String usedModifierText = this.rawDescription;
    public CustomLegendaryMagic() {
        super(ID, -1, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.isLegendarySpell = true;
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
