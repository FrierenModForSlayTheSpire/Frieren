package FrierenMod.cards.white;

import FrierenMod.actions.DamagePerAttackPlayedAction;
import FrierenMod.actions.ModifyCostAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HellFireSummoning extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(HellFireSummoning.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final CardInfo info = new CardInfo(ID, 0, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.RARE, CardTarget.ENEMY);

    public HellFireSummoning() {
        super(info);
    }

//    public HellFireSummoning(CardColor color) {
//        super(ID, 0, CardType.ATTACK, color, CardRarity.RARE, CardTarget.ENEMY);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.damage = this.baseDamage = 8;
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
        this.addToBot(new DamagePerAttackPlayedAction(DamagePerAttackPlayedAction.Type.ChantCardUsedTimes, m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        this.addToBot(new ModifyCostAction(this.uuid, 1));
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void applyPowers() {
        super.applyPowers();
        int count = CombatHelper.getChantCardUsedThisTurn();
        this.rawDescription = cardStrings.DESCRIPTION;
        if (count > 0) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count + cardStrings.EXTENDED_DESCRIPTION[1];
        }
        initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard cardPlayed) {
        if (cardPlayed instanceof AbstractBaseCard && ((AbstractBaseCard) cardPlayed).isChantCard)
            this.superFlash();
    }
}
