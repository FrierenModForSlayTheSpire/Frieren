package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Laziness;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FiveMinutesMore extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(FiveMinutesMore.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON);

    public FiveMinutesMore() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.baseMagicNumber = this.magicNumber = 2;
        this.cardsToPreview = new Laziness();
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
        this.addToBot(new GainEnergyAction(2));
        if (!upgraded)
            this.addToBot(new MakeTempCardInDrawPileAction(this.cardsToPreview.makeCopy(), 1, true, true));
        else
            this.addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeCopy(), 1));
    }
}
