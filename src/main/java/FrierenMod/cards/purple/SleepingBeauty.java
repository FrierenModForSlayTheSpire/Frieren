package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.powers.ConcentrationPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SleepingBeauty extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(SleepingBeauty.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.RARE, CardTarget.SELF);

    public SleepingBeauty() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.magicNumber = this.baseMagicNumber = 6;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int times = Math.min(CombatHelper.getConcentrationPowerAmt(), this.magicNumber);
        int i = 0;
        while (i < times) {
            this.addToBot(new GainEnergyAction(1));
            this.addToBot(new ReducePowerAction(p, p, ConcentrationPower.POWER_ID, 1));
            i++;
            if (i >= times)
                break;
            this.addToBot(new DrawCardAction(p, 1));
            this.addToBot(new ReducePowerAction(p, p, ConcentrationPower.POWER_ID, 1));
            i++;
        }
    }
}

