package FrierenMod.cards.whitePurple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.FlyingMagicPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

public class FlyingMagic extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(FlyingMagic.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 3, CardType.POWER, CardEnums.FRIEREN_CARD, CardRarity.RARE);
    public static final CardInfo info2 = new CardInfo(ID, 3, CardType.POWER, CardEnums.FERN_CARD, CardRarity.RARE, true);

    public FlyingMagic() {
        super(info);
    }

    public FlyingMagic(boolean forSecondRegister) {
        super(info2);
    }

    @Override
    public AbstractCard getCardForSecondRegister() {
        return new FlyingMagic(true);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.tags.add(Enum.FRIEREN_FERN_CARD);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(-1);
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 3), 3));
        this.addToBot(new ApplyPowerAction(p, p, new FlyingMagicPower(p, this.magicNumber)));
    }
}
