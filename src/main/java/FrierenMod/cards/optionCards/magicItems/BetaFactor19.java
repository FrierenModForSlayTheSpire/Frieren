package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BetaFactor19 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor19.class.getSimpleName());

    public BetaFactor19() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.COMMON;
        this.rewardAddCoefficient = 2;
    }

    @Override
    public void takeEffect() {
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -this.secondMagicNumber), -this.secondMagicNumber));
            if (mo != null && !mo.hasPower("Artifact"))
                addToBot(new ApplyPowerAction(mo, p, new GainStrengthPower(mo, this.secondMagicNumber), this.secondMagicNumber));
        }
    }
}
