package FrierenMod.cards.purple;

import FrierenMod.actions.ModifyMagicNumberAction;
import FrierenMod.actions.ModifyRaidNumberAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SaturationOffensiveMagic extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(SaturationOffensiveMagic.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardType.ATTACK, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);

    public SaturationOffensiveMagic() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.damage = this.baseDamage = 5;
        this.magicNumber = this.baseMagicNumber = 1;
        this.raidNumber = this.baseRaidNumber = 0;
        this.tags.add(Enum.RAID);
        this.isEthereal = true;
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
        for (int i = 0; i < this.magicNumber; i++) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        this.isRaidTriggered = CombatHelper.triggerRaid(raidNumber, () -> {
            this.addToBot(new ModifyMagicNumberAction(this.uuid, 1));
            this.addToBot(new ModifyRaidNumberAction(this.uuid, 1));
        });
    }
}

