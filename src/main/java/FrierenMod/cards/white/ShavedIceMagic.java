package FrierenMod.cards.white;

import FrierenMod.actions.MakeMagicPowerInDrawPileAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShavedIceMagic extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(ShavedIceMagic.class.getSimpleName());
    public ShavedIceMagic() {
        super(ID, 0, CardRarity.COMMON);
        this.baseMagicNumber = this.magicNumber = 4;
        this.isMagicSource= true;
        this.cardsToPreview = new MagicPower();
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeMagicPowerInDrawPileAction(this.magicNumber,canGainMagic));
    }
}
