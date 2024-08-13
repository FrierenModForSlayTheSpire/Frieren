package FrierenMod.cards.white;

import FrierenMod.actions.DefensiveMagicAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.SecondDefensiveMagic;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DefensiveMagic extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(DefensiveMagic.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardEnums.FRIEREN_CARD, CardRarity.COMMON);

    public DefensiveMagic() {
        super(info);
    }

//    public DefensiveMagic(CardColor color) {
//        super(ID, 1, color, CardRarity.COMMON);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.block = this.baseBlock = 3;
        this.chantX = this.baseChantX = 1;
        this.tags.add(AbstractBaseCard.Enum.CHANT);
        SecondDefensiveMagic c = new SecondDefensiveMagic();
        if (this.upgraded) {
            c.upgrade();
        }
        this.cardsToPreview = c;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.cardsToPreview.upgrade();
            this.upgradeBlock(2);
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DefensiveMagicAction(this.chantX, this.block, this.upgraded, this.cardsToPreview));
    }
}
