package FrierenMod.cards.white;

import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShavedIceSpell extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(ShavedIceSpell.class.getSimpleName());
    public ShavedIceSpell() {
        super(ID, 0, CardRarity.COMMON);
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
