package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.PerpetualPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Perpetual extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Perpetual.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.POWER, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.SELF);

    public Perpetual() {
        super(info);
    }

//    public Perpetual(CardColor color) {
//        super(ID, 1, CardType.POWER, color, CardRarity.UNCOMMON, CardTarget.SELF);
//    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new PerpetualPower(p, 1), 1));
    }
}
