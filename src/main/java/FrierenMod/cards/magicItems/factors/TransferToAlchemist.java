package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;

public class TransferToAlchemist extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(TransferToAlchemist.class.getSimpleName());

    public TransferToAlchemist() {
        super(ID);
        loadRarity(MagicItemRarity.COMMON);
        this.rewardMultipleCoefficient = 2;
    }

    @Override
    public void takeEffect() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                if (!monster.isDead && !monster.isDying) {
                    addToBot(new ApplyPowerAction(monster, p, new PoisonPower(monster, p, this.secondMagicNumber), this.secondMagicNumber));
                }
            }
        }
    }
}
