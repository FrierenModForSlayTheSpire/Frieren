package FrierenMod.cards.white;

import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class RestraintMagic extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(RestraintMagic.class.getSimpleName());

    public RestraintMagic() {
        super(ID, 3, CardType.SKILL, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public RestraintMagic(CardColor color) {
        super(ID, 3, CardType.SKILL, color, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.baseMagicNumber = magicNumber = 3;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -3), -3));
        this.addToBot(new ApplyPowerAction(m, p, new SlowPower(m, 0), 0));
    }
}
