package FrierenMod.cards.canAutoAdd.white;

import FrierenMod.actions.TimeTravelAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TimeConcept extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(TimeConcept.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardEnums.FRIEREN_CARD, CardRarity.RARE);
    public TimeConcept() {
        super(info);
    }
//    public TimeConcept(CardColor color) {
//        super(ID, 2, color, CardRarity.RARE);
//    }
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
        this.addToBot(new TimeTravelAction(this,upgraded));
    }
}
