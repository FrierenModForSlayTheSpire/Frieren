package FrierenMod.cards.canAutoAdd.optionCards;

import FrierenMod.actions.DoubleManaInDiscardPileAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.canAutoAdd.tempCards.Mana;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DoubleMagicInDiscardPile extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(DoubleMagicInDiscardPile.class.getSimpleName());
    public DoubleMagicInDiscardPile() {
        super(ID, -2, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.cardsToPreview = new Mana();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }
    public void onChoseThisOption() {
        this.addToBot(new DoubleManaInDiscardPileAction());
    }
}
