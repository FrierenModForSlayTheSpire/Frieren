package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.RitesPreparationPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RitesPreparation extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(RitesPreparation.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.POWER, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON);

    public RitesPreparation() {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.magicNumber = baseMagicNumber = 3;
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
        this.addToBot(new ApplyPowerAction(p, p, new RitesPreparationPower(p, magicNumber), magicNumber));
    }
}
