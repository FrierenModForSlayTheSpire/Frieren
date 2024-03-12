package FrierenMod.cards.white;

import FrierenMod.actions.ModifyCostAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.gameHelpers.LegendMagicHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HellFireSummoning extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(HellFireSummoning.class.getSimpleName());
    public HellFireSummoning() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.damage = this.baseDamage = 8;
        this.magicNumber = this.baseMagicNumber = 0;
        this.isBackFireCard = true;
        this.isSealCard = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int times = LegendMagicHelper.getChantCardUsedThisTurn();
        if (p.hasRelic("Chemical X")) {
            times += 2;
            p.getRelic("Chemical X").flash();
        }
        for (int i = 0; i < times * 2; i++) {
            this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.FIRE));
        }
        this.addToBot(new ModifyCostAction(this.uuid,1));
    }
    public void applyPowers() {
        this.baseMagicNumber = LegendMagicHelper.getChantCardUsedThisTurn();
        super.applyPowers();
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;
        this.initializeDescription();
    }
    public void calculateCardDamage(AbstractMonster mo) {
        this.baseMagicNumber = LegendMagicHelper.getChantCardUsedThisTurn();
        super.calculateCardDamage(mo);
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;
        this.initializeDescription();
    }
    @Override
    public void triggerOnOtherCardPlayed(AbstractCard cardPlayed) {
        if(cardPlayed instanceof AbstractFrierenCard && ((AbstractFrierenCard) cardPlayed).isChantCard)
            this.superFlash();
    }
}
