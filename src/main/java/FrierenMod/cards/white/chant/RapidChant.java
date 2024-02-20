package FrierenMod.cards.white.chant;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RapidChant extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(RapidChant.class.getSimpleName());
    public RapidChant() {
        super(ID, 0, CardRarity.COMMON);
        this.isChantCard = true;
        this.chantX = this.baseChantX = 3;
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
    public void use(AbstractPlayer p, AbstractMonster m){
        this.addToBot(new ChantAction(this.chantX));
    }
}
