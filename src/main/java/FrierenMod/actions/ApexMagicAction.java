package FrierenMod.actions;

import FrierenMod.gameHelpers.CombatHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;

public class ApexMagicAction extends AbstractGameAction {

    private final int magicNumber;

    public ApexMagicAction(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public void update() {
        if (CombatHelper.getManaNumInDrawPile() == 4 && CombatHelper.getManaNumInHand() == 4 && CombatHelper.getManaNumInDiscardPile() == 4) {
            if (Settings.FAST_MODE) {
                this.addToBot(new VFXAction(new GrandFinalEffect(), 0.7F));
            } else {
                this.addToBot(new VFXAction(new GrandFinalEffect(), 1.0F));
            }
            for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters)
                if(!mo.halfDead && !mo.isDying && !mo.isEscaping)
                    this.addToBot(new InstantKillAction(mo));
        } else {
            this.addToBot(new DrawCardAction(this.magicNumber));
        }
        this.isDone = true;
    }
}
