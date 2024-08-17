package FrierenMod.relics;

import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.ModInformation;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;

public class IcicleCherryBlossom extends AbstractBaseRelic implements ClickableRelic {
    public static final String ID = ModInformation.makeID(IcicleCherryBlossom.class.getSimpleName());

    public IcicleCherryBlossom() {
        super(ID, RelicTier.UNCOMMON);
        this.counter = 10;
        this.description = String.format(DESCRIPTIONS[0], this.counter);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public String getUpdatedDescription() {
        return String.format(this.DESCRIPTIONS[0], this.counter);
    }

    @Override
    public void onRightClick() {
        if (!usedUp && CombatHelper.isInCombat()) {
            --this.counter;
            this.description = String.format(DESCRIPTIONS[0], this.counter);
            this.tips.clear();
            this.tips.add(new PowerTip(this.name, this.description));
            this.initializeTips();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new MakeManaInDrawPileAction(1));
            if (counter <= 0)
                usedUp();
        }
    }
}