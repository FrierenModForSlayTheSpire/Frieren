package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;

public class BetaFactor21 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor21.class.getSimpleName());

    public BetaFactor21() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.COMMON;
        this.rewardMultipleCoefficient = 2;
    }

    @Override
    public void takeEffect() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                if (!monster.isDead && !monster.isDying) {
                    addToBot(new ApplyPowerAction(monster, p, new PoisonPower(monster, p, this.magicNumber), this.magicNumber));
                }
            }
        }
    }
}
