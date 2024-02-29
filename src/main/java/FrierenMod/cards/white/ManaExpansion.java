package FrierenMod.cards.white;

import FrierenMod.actions.ManaExpandAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ManaExpansion extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(ManaExpansion.class.getSimpleName());
    public ManaExpansion() {
        super(ID, 1, CardRarity.RARE);
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
