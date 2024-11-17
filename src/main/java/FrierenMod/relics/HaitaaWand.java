package FrierenMod.relics;

import FrierenMod.actions.ExhaustAllManaAction;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.powers.ConcentrationPower;
import FrierenMod.utils.ModInformation;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

@AutoAdd.Ignore
public class HaitaaWand extends AbstractBaseRelic {
    public static final String ID = ModInformation.makeID(HaitaaWand.class.getSimpleName());

    public HaitaaWand() {
        super(ID, RelicTier.STARTER);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new HaitaaWand();
    }

    @Override
    public void atTurnStart() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new ApplyPowerAction(p, p, new ConcentrationPower(1)));
        this.flash();
    }

    @Override
    public void onPlayerEndTurn() {
        int amt = CombatHelper.getAllManaNum();
        if (amt > 0) {
            this.flash();
            AbstractPlayer p = AbstractDungeon.player;
            this.addToBot(new ExhaustAllManaAction());
            this.addToBot(new ApplyPowerAction(p, p, new ConcentrationPower(amt)));
        }
    }
}
