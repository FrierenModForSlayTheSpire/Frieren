package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.RingletFormPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RingletForm extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(RingletForm.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardType.POWER, CardEnums.FRIEREN_CARD, CardRarity.RARE);

    public RingletForm() {
        super(info);
    }

//    public RingletForm(CardColor color) {
//        super(ID, 2, CardType.POWER, color, CardRarity.RARE);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new RingletFormPower(p, this.magicNumber)));
    }
}
