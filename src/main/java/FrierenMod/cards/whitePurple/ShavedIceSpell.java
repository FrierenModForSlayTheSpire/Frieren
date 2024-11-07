package FrierenMod.cards.whitePurple;

import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShavedIceSpell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ShavedIceSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardEnums.FRIEREN_CARD, CardRarity.COMMON);
    public static final CardInfo info2 = new CardInfo(ID, 0, CardEnums.FERN_CARD, CardRarity.COMMON, true);

    public ShavedIceSpell() {
        super(info);
    }

    public ShavedIceSpell(boolean forSecondRegister) {
        super(info2);
    }

    @Override
    public AbstractCard getCardForSecondRegister() {
        return new ShavedIceSpell(true);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.tags.add(Enum.FRIEREN_FERN_CARD);
        this.baseMagicNumber = this.magicNumber = 3;
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
