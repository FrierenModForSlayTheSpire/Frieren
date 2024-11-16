package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.powers.ConcentrationPower;
import FrierenMod.powers.FusionPower.ConcentrationFusionPower;
import FrierenMod.powers.FusionPower.DamageFusionPower;
import FrierenMod.powers.FusionPower.DrawCardFusionPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FusionRaid extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(FusionRaid.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.ATTACK, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);

    public FusionRaid() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.damage = this.baseDamage = 8;
        this.raidNumber = this.baseRaidNumber = 2;
        this.magicNumber = this.baseMagicNumber = 2;
        this.tags.add(Enum.RAID);
        this.tags.add(Enum.FUSION);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        if (CombatHelper.canRaidTakeEffect(1) || CombatHelper.canRaidTakeEffect(2) || CombatHelper.canRaidTakeEffect(3)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        boolean triggered1 = CombatHelper.triggerRaid(1, () -> {
            this.addToBot(new ApplyPowerAction(p, p, new ConcentrationPower(this.magicNumber)));
            this.addToBot(new ApplyPowerAction(p, p, new ConcentrationFusionPower(this.magicNumber)));
        });
        boolean triggered2 = CombatHelper.triggerRaid(2, true, () -> {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            this.addToBot(new ApplyPowerAction(p, p, new DamageFusionPower(this.damage)));
        });
        boolean triggered3 = CombatHelper.triggerRaid(3, () -> {
            this.addToBot(new DrawCardAction(2));
            this.addToBot(new ApplyPowerAction(p, p, new DrawCardFusionPower(2)));
        });
        this.isRaidTriggered = triggered1 || triggered2 || triggered3;
    }
}

