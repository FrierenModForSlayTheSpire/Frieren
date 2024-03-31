package FrierenMod.cards.canAutoAdd.tempCards;

import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;

public class CustomLegendaryMagic extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(CustomLegendaryMagic.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -1, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    public String usedModifierText = this.rawDescription;
    public CustomLegendaryMagic() {
        super(info);
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
