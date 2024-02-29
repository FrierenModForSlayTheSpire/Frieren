package FrierenMod.cards.white;

import FrierenMod.actions.DrawMagicAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SwampSpell extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(SwampSpell.class.getSimpleName());
    public SwampSpell() {
        super(ID, 1, CardRarity.COMMON);
        this.magicNumber = this.baseMagicNumber = 3;
        this.cardsToPreview = new Mana();
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawMagicAction(this.magicNumber));
    }
}