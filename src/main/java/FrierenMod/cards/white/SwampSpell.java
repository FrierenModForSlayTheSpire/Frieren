package FrierenMod.cards.white;

import FrierenMod.actions.DrawMagicAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.canAutoAdd.tempCards.Mana;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SwampSpell extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(SwampSpell.class.getSimpleName());

    public SwampSpell() {
        super(ID, 1, CardEnums.FRIEREN_CARD, CardRarity.COMMON);
    }

    public SwampSpell(CardColor color) {
        super(ID, 1, color, CardRarity.COMMON);
    }

    @Override
    public void initSpecifiedAttributes() {
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
