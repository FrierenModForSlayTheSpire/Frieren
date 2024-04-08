package FrierenMod.cards.canAutoAdd.white;

import FrierenMod.actions.SerieGrimoireAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SerieGrimoire extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(SerieGrimoire.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardEnums.FRIEREN_CARD, CardRarity.RARE);
    public SerieGrimoire() {
        super(info);
    }
//    public SerieGrimoire(CardColor color) {
//        super(ID, 0, color, CardRarity.RARE);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.exhaust = true;
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
        this.addToBot(new SerieGrimoireAction(this.upgraded));
    }
}
