package FrierenMod.cards.white;

import FrierenMod.actions.TimeTravelAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.curses.Regret;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TimeConcept extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(TimeConcept.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardEnums.FRIEREN_CARD, CardRarity.RARE);

    public TimeConcept() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.cardsToPreview = new Regret();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new TimeTravelAction(this, upgraded));
    }
}
