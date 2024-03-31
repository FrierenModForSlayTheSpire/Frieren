package FrierenMod.cards.white.chant;

import FrierenMod.actions.DefensiveMagicAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.tempCards.SecondDefensiveMagic;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DefensiveMagic extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(DefensiveMagic.class.getSimpleName());
    public DefensiveMagic() {
        super(ID, 1, CardRarity.COMMON);
        this.block = this.baseBlock = 3;
        this.chantX = this.baseChantX = 1;
        this.isChantCard = true;
        SecondDefensiveMagic c = new SecondDefensiveMagic();
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
        this.addToBot(new DefensiveMagicAction(this.chantX,this.block,this.upgraded,this.cardsToPreview));
    }
}
