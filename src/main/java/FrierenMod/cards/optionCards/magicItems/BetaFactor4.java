package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BetaFactor4 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor4.class.getSimpleName());

    public BetaFactor4() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.COMMON;
        this.rewardMultipleCoefficient = 4;
    }

    @Override
    public void takeEffect() {
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            addToBot(new LoseHPAction(mo, p, secondMagicNumber));
        }
    }
}
