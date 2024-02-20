package FrierenMod.cards.white;

import FrierenMod.actions.MakeMagicPowerInDiscardAction;
import FrierenMod.actions.MakeMagicPowerInDrawPileAction;
import FrierenMod.actions.MakeMagicPowerInHandAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Disperse extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(Disperse.class.getSimpleName());
    public Disperse() {
        super(ID, 1, CardRarity.COMMON);
        this.exhaust = true;
        this.cardsToPreview = new MagicPower();
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
        this.addToBot(new MakeMagicPowerInDrawPileAction(1));
        this.addToBot(new MakeMagicPowerInHandAction(2));
        this.addToBot(new MakeMagicPowerInDiscardAction(3));
    }
}
