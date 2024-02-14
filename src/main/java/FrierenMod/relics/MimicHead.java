package FrierenMod.relics;

import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
public class MimicHead extends AbstractFrierenRelic {
    public static final String ID = ModInformation.makeID(MimicHead.class.getSimpleName());

    public MimicHead() {
        super(ID, RelicTier.UNCOMMON);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    public void onChestOpen(boolean bossChest) {
        if (!bossChest) {
            flash();
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.getCurrRoom().addGoldToRewards(100);
        }
    }

    public AbstractRelic makeCopy() {
        return new MimicHead();
    }
}