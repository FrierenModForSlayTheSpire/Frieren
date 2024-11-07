package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CleanClothSpell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(CleanClothSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardType.ATTACK, CardEnums.FERN_CARD, CardRarity.RARE, CardTarget.ENEMY);

    public CleanClothSpell() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.damage = this.baseDamage = 50;
        this.raidNumber = this.baseRaidNumber = 10;
        this.tags.add(Enum.RAID);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(10);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.isRaidTriggered = CombatHelper.triggerRaid(raidNumber, true, () -> this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL)));
    }
}