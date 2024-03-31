package FrierenMod.cards.canAutoAdd.white;

import FrierenMod.actions.SeeThroughPhantomAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SeeThroughPhantom extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(SeeThroughPhantom.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.COMMON, CardTarget.ENEMY);

    public SeeThroughPhantom() {
        super(info);
    }

//    public SeeThroughPhantom(CardColor color) {
//        super(ID, 1, CardType.ATTACK, color, CardRarity.COMMON, CardTarget.ENEMY);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.damage = this.baseDamage = 8;
        this.baseMagicNumber = this.magicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SeeThroughPhantomAction(this.magicNumber, this.damage, this.damageTypeForTurn, m));
    }
}
