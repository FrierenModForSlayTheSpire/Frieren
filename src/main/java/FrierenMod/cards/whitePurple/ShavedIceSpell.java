package FrierenMod.cards.whitePurple;

import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.cards.DualCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShavedIceSpell extends DualCard {
    public static final String ID = ModInformation.makeID(ShavedIceSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardEnums.FRIEREN_CARD, CardRarity.COMMON);
    public static final CardInfo info2 = new CardInfo(ID, 0, CardEnums.FERN_CARD, CardRarity.COMMON,true);
    public ShavedIceSpell() {
        super(info);
    }

    public ShavedIceSpell(CardInfo info) {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.isFrierenFernCard = true;
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
