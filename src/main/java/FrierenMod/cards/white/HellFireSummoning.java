package FrierenMod.cards.white;

import FrierenMod.actions.ModifyCostAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.LegendarySpellHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HellFireSummoning extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(HellFireSummoning.class.getSimpleName());

    public HellFireSummoning() {
        super(ID, 0, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.RARE, CardTarget.ENEMY);
    }

    public HellFireSummoning(CardColor color) {
        super(ID, 0, CardType.ATTACK, color, CardRarity.RARE, CardTarget.ENEMY);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.damage = this.baseDamage = 8;
        this.magicNumber = this.baseMagicNumber = 0;
        this.isCostResetCard = true;
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
        int times = LegendarySpellHelper.getChantCardUsedThisTurn();
        if (p.hasRelic("Chemical X")) {
            times += 2;
            p.getRelic("Chemical X").flash();
        }
        for (int i = 0; i < times * 2; i++) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        }
        this.addToBot(new ModifyCostAction(this.uuid, 1));
    }

    public void applyPowers() {
        this.baseMagicNumber = LegendarySpellHelper.getChantCardUsedThisTurn();
        super.applyPowers();
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;
        this.initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        this.baseMagicNumber = LegendarySpellHelper.getChantCardUsedThisTurn();
        super.calculateCardDamage(mo);
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard cardPlayed) {
        if (cardPlayed instanceof AbstractMagicianCard && ((AbstractMagicianCard) cardPlayed).isChantCard)
            this.superFlash();
    }
}
