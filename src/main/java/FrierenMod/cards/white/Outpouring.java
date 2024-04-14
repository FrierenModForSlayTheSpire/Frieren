package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.OutpouringPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Outpouring extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Outpouring.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON);

    public Outpouring() {
        super(info);
    }

//    public Outpouring(CardColor color) {
//        super(ID, 1, color, CardRarity.UNCOMMON);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.selfRetain = true;
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new OutpouringPower(p, this.magicNumber)));
    }
}
