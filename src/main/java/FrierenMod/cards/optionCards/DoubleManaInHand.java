package FrierenMod.cards.optionCards;

import FrierenMod.actions.DoubleManaInHandAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DoubleManaInHand extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(DoubleManaInHand.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -2, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    public DoubleManaInHand() {
        super(info);
        this.cardsToPreview = new Mana();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }
    public void onChoseThisOption() {
        this.addToBot(new DoubleManaInHandAction());
    }
}
