package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.SimmeringPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Simmering extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Simmering.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.POWER, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON);

    public Simmering() {
        super(info);
    }

//    public Simmering(CardColor color) {
//        super(ID, 1, color, CardRarity.UNCOMMON);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.cardsToPreview = new Mana();
        this.magicNumber = this.baseMagicNumber = 4;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(6);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new SimmeringPower(p, this.magicNumber)));
    }
}
