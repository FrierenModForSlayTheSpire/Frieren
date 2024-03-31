package FrierenMod.cards.white;

import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.tempCards.Laziness;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FiveMinutesMore extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(FiveMinutesMore.class.getSimpleName());
    public FiveMinutesMore() {
        super(ID, 0, CardRarity.UNCOMMON);
        this.baseMagicNumber=this.magicNumber=2;
        this.cardsToPreview = new Laziness();
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
        this.addToBot(new GainEnergyAction(this.magicNumber));
        this.addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeCopy(),1));
    }
}
