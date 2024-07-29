package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class BetaFactor7 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor7.class.getSimpleName());

    public BetaFactor7() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.UNCOMMON;
        this.manaNeedMultipleCoefficient = 2;
    }

    @Override
    public void takeEffect() {
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }
}
