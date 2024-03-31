package FrierenMod.cards.white;

import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.canAutoAdd.tempCards.Mana;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShavedIceSpell extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(ShavedIceSpell.class.getSimpleName());

    public ShavedIceSpell() {
        super(ID, 0, CardEnums.FRIEREN_CARD, CardRarity.COMMON);
    }

    public ShavedIceSpell(CardColor color) {
        super(ID, 0, color, CardRarity.COMMON);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.baseMagicNumber = this.magicNumber = 4;
        this.cardsToPreview = new Mana();
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
        this.addToBot(new MakeManaInDrawPileAction(this.magicNumber));
    }
}
