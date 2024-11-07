package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SeriousSeriesSpell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(SeriousSeriesSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardType.ATTACK, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);

    public SeriousSeriesSpell() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.damage = this.baseDamage = 18;
        this.raidNumber = this.baseRaidNumber = 3;
        this.tags.add(Enum.RAID);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeRaidNumber(-1);
        }
    }

    @Override
    public void applyPowers() {
        if (CombatHelper.canRaidTakeEffect(this.raidNumber)) {
            this.target = CardTarget.ALL_ENEMY;
            this.isMultiDamage = true;
        } else {
            this.target = CardTarget.ENEMY;
            this.isMultiDamage = false;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!CombatHelper.canRaidTakeEffect(this.raidNumber))
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        else
            this.isRaidTriggered = CombatHelper.triggerRaid(raidNumber, () -> this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL)));
    }
}

