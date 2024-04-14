package FrierenMod.cards.white;

import FrierenMod.actions.PreciseChantAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PreciseChant extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(PreciseChant.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON);

    public PreciseChant() {
        super(info);
    }

//    public PreciseChant(CardColor color) {
//        super(ID, 1, color, CardRarity.UNCOMMON);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.isChantCard = true;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new PreciseChantAction());
    }
}
