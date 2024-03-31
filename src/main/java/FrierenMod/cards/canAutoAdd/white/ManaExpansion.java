package FrierenMod.cards.canAutoAdd.white;

import FrierenMod.actions.ManaExpandAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ManaExpansion extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ManaExpansion.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardEnums.FRIEREN_CARD, CardRarity.RARE);

    public ManaExpansion() {
        super(info);
    }

//    public ManaExpansion(CardColor color) {
//        super(ID, 1, color, CardRarity.RARE);
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
        this.addToBot(new ManaExpandAction(upgraded));
    }
}
