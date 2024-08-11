package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IceArrows extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(IceArrows.class.getSimpleName());

    public IceArrows() {
        super(ID);
        loadRarity(MagicItemRarity.COMMON);
        this.rewardMultipleCoefficient = 4;
    }

    @Override
    public void takeEffect() {
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            addToBot(new LoseHPAction(mo, p, secondMagicNumber));
        }
    }
}
