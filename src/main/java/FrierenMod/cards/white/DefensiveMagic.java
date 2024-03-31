package FrierenMod.cards.white;

import FrierenMod.actions.DefensiveMagicAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.canAutoAdd.tempCards.SecondDefensiveMagic;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DefensiveMagic extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(DefensiveMagic.class.getSimpleName());

    public DefensiveMagic() {
        super(ID, 1, CardEnums.FRIEREN_CARD, CardRarity.COMMON);
    }

    public DefensiveMagic(CardColor color) {
        super(ID, 1, color, CardRarity.COMMON);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.block = this.baseBlock = 3;
        this.chantX = this.baseChantX = 1;
        this.isChantCard = true;
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
