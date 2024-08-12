package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.OpenTheWaygatePower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OpenTheWaygate extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(OpenTheWaygate.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardType.POWER, CardEnums.FRIEREN_CARD, CardRarity.RARE, CardTarget.SELF);

    public OpenTheWaygate() {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.magicNumber = this.baseMagicNumber = 5;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(-1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new OpenTheWaygatePower(p, magicNumber)));
    }
}
