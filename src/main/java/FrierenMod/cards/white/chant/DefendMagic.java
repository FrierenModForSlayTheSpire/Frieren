package FrierenMod.cards.white.chant;

import FrierenMod.actions.DefendMagicAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.SecondDefendMagic;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DefendMagic extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(DefendMagic.class.getSimpleName());
    public DefendMagic() {
        super(ID, 1, CardRarity.COMMON);
        this.block = this.baseBlock = 3;
        this.chantX = this.baseChantX = 1;
        this.isChantCard = true;
        SecondDefendMagic c = new SecondDefendMagic();
        if(this.upgraded) {
            c.upgrade();
        }
        this.cardsToPreview = c;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(2);
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DefendMagicAction(this.chantX,this.block,this.upgraded,this.cardsToPreview));
    }
}
