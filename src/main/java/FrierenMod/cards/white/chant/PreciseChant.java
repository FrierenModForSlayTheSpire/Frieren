package FrierenMod.cards.white.chant;

import FrierenMod.actions.PreciseChantAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PreciseChant extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(PreciseChant.class.getSimpleName());
    public PreciseChant() {
        super(ID, 1, CardRarity.UNCOMMON);
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
