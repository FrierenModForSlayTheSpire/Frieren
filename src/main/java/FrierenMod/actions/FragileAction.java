package FrierenMod.actions;

import FrierenMod.cards.canAutoAdd.purple.Fragile;
import FrierenMod.gameHelpers.CombatHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class FragileAction extends AbstractGameAction {
    private final Fragile c;

    public FragileAction(Fragile c) {
        this.c = c;
    }

    @Override
    public void update() {
        int randomNumber = cardRandomRng.random(100);
        if (randomNumber < 20) {
            this.addToBot(new DrawCardAction(c.magicNumber));
        } else if (randomNumber < 40) {
            this.addToBot(new GainEnergyAction(c.magicNumber));
        } else if (randomNumber < 60) {
            this.addToBot(new ModifyPowerStackAmtAction(CombatHelper.getConcentrationPower(), c.magicNumber, false));
        } else if (randomNumber < 80) {
            c.upgradeRaidNumber(-1);
            if (c.raidNumber < 0) {
                this.addToBot(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                c.raidNumber = c.baseRaidNumber = 0;
                this.isDone = true;
                return;
            }
            this.upgradeMagicNumber();
        }else {
            this.addToBot(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
        }
        this.isDone = true;
    }

    private void upgradeMagicNumber() {
        c.baseMagicNumber += 1;
        c.magicNumber = c.baseMagicNumber;
        c.upgradedMagicNumber = true;
    }
}
