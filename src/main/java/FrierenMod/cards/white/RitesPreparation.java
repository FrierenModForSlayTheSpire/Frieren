package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.powers.RitesPreparationPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RitesPreparation extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(RitesPreparation.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.POWER, CardEnums.FRIEREN_CARD, CardRarity.COMMON);

    public RitesPreparation() {
        super(info);
    }

//    public RitesPreparation(CardColor color) {
//        super(ID, 1, CardType.POWER, color, CardRarity.COMMON);
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
        this.addToBot(new ApplyPowerAction(p, p, new RitesPreparationPower(p, 1), 1));
    }
}
