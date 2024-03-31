package FrierenMod.cards.canAutoAdd.optionCards;

import FrierenMod.actions.DoubleManaInDrawPileAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.canAutoAdd.tempCards.Mana;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DoubleMagicInDrawPile extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(DoubleMagicInDrawPile.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -2, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    public DoubleMagicInDrawPile() {
        super(info);
        this.cardsToPreview = new Mana();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }
    public void onChoseThisOption() {
        this.addToBot(new DoubleManaInDrawPileAction());
    }
}
