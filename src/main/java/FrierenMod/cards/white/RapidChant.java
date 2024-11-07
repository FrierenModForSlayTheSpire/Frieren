package FrierenMod.cards.white;

import FrierenMod.actions.ChantAction;
import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.DualCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RapidChant extends DualCard {
    public static final String ID = ModInformation.makeID(RapidChant.class.getSimpleName());
    public static CardInfo info = new CardInfo(ID, 0, CardEnums.FRIEREN_CARD, CardRarity.COMMON);
    public RapidChant() {
        super(info);
    }
    public RapidChant(CardInfo info) {
        super(info);
    }

//    public RapidChant(CardColor color) {
//        super(ID, 0, color, CardRarity.COMMON);
//    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.isFrierenFernCard = true;
        this.tags.add(AbstractBaseCard.Enum.CHANT);
        this.chantX = this.baseChantX = 1;
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

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChantAction(this.chantX));
        this.addToBot(new MakeManaInDrawPileAction(1));
    }
}
