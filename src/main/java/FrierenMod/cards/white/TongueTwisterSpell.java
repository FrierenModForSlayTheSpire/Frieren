package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TongueTwisterSpell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(TongueTwisterSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardEnums.FRIEREN_CARD, CardRarity.COMMON);

    public TongueTwisterSpell() {
        super(info);
    }

//    public TongueTwisterSpell(CardColor color) {
//        super(ID, 1, color, CardRarity.COMMON);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.magicNumber = this.baseMagicNumber = 2;
        this.cardsToPreview = new Mana();
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
        if (CombatHelper.getManaNumInHand() > 0) {
            this.addToBot(new DrawCardAction(p, this.magicNumber));
        } else {
            this.addToBot(new DrawCardAction(p, this.magicNumber + 1));
        }
    }
}
