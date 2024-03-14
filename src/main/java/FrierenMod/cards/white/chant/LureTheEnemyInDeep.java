package FrierenMod.cards.white.chant;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LureTheEnemyInDeep extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(LureTheEnemyInDeep.class.getSimpleName());
    public LureTheEnemyInDeep() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.isChantCard = true;
        this.chantX = this.baseChantX = 1;
        this.magicNumber = this.baseMagicNumber = 1;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.upgradeName();
            this.upgradeChantX(1);
            this.upgradeMagicNumber(1);
            this.exhaust=true;
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChantAction(this.chantX,true,new DrawCardAction(this.magicNumber)));
    }
}
