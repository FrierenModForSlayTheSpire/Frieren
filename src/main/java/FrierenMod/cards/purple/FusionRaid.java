package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.powers.FusionPower.DrawCardFusionPower;
import FrierenMod.powers.FusionPower.EnergyFusionPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FusionRaid extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(FusionRaid.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardType.ATTACK, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);

    public FusionRaid() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.damage = this.baseDamage = 20;
        this.raidNumber = this.baseRaidNumber = 2;
        this.isMultiDamage = true;
        this.tags.add(Enum.RAID);
        this.tags.add(Enum.FUSION);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(5);
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
            this.addToBot(new DrawCardAction(2));
            this.addToBot(new ApplyPowerAction(p, p, new DrawCardFusionPower(2)));
        });
        boolean triggered2 = CombatHelper.triggerRaid(2, true, () -> this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL)));
        boolean triggered3 = CombatHelper.triggerRaid(3, () -> {
            this.addToBot(new GainEnergyAction(2));
            this.addToBot(new ApplyPowerAction(p, p, new EnergyFusionPower(2)));
        });
        this.isRaidTriggered = triggered1 || triggered2 || triggered3;
    }
}

