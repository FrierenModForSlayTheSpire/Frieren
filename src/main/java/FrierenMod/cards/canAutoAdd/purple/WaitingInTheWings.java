package FrierenMod.cards.canAutoAdd.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.powers.ConcentrationPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WaitingInTheWings extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(WaitingInTheWings.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.ATTACK, CardEnums.FERN_CARD, CardRarity.COMMON, CardTarget.ENEMY);

    public WaitingInTheWings() {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.block = baseBlock = 5;
        this.damage = this.baseDamage = 8;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(2);
            this.upgradeDamage(2);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = (CombatHelper.isDeviationEven(false)) ? AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy() : AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

    @Override
    public void applyPowers() {
        if(!CombatHelper.isDeviationEven(false)){
            this.returnToHand = true;
            this.target = CardTarget.NONE;
        }
        else {
            this.returnToHand = false;
            this.target = info.cardTarget;
        }
        super.applyPowers();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (CombatHelper.isDeviationEven(true)) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        } else {
            this.addToBot(new ApplyPowerAction(p, p, new ConcentrationPower(p, 1)));
            this.addToBot(new GainBlockAction(p, this.block));
        }
    }
}

