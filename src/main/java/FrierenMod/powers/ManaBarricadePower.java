package FrierenMod.powers;

import FrierenMod.gameHelpers.ChantHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BarricadePower;


public class ManaBarricadePower extends AbstractFrierenPower {
    public static final String POWER_ID = ModInformation.makeID(ManaBarricadePower.class.getSimpleName());
    public ManaBarricadePower(AbstractCreature owner) {
        super(POWER_ID, owner, PowerType.BUFF);
    }

    private final AbstractPlayer p = AbstractDungeon.player;
    public void atEndOfTurn(boolean isPlayer) {
        if (ChantHelper.getAllManaNum() >= 5) {
            boolean powerExists = false;
            for (AbstractPower pow : p.powers) {
                if (pow.ID.equals("Barricade")) {
                    powerExists = true;
                    break;
                }
            }
            if (!powerExists)
                this.addToBot(new ApplyPowerAction(p, p,new BarricadePower(p)));
        }
        else
            if(p.hasPower("Barricade"))
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Barricade"));
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0]);
    }
}