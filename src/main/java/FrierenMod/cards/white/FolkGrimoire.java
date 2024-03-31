package FrierenMod.cards.white;

import FrierenMod.actions.MagicBookAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FolkGrimoire extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(FolkGrimoire.class.getSimpleName());
    public FolkGrimoire() {
        super(ID, 2, CardRarity.UNCOMMON);
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
        this.addToBot(new MagicBookAction(this.upgraded));
    }
}
