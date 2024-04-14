package FrierenMod.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;

public class ChantFromHandAction extends ChantFromCardGroupAction {

    public ChantFromHandAction(int manaExhaust, int reward, boolean haveNotTriggered) {
        super(manaExhaust, reward);
        this.haveNotTriggered = haveNotTriggered;
    }

    public ChantFromHandAction(int manaExhaust, int reward, AbstractGameAction... nextAction) {
        super(manaExhaust, reward);
        this.nextAction = nextAction;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new VFXAction(new BorderLongFlashEffect(Color.FIREBRICK, true)));
        this.addToBot(new VFXAction(p, new InflameEffect(p), 1.0F));
        if (this.manaExhaust > 0)
            this.addToBot(new ExhaustManaInHandAction(this.manaExhaust));
        this.addToBot(new VFXAction(new BorderFlashEffect(Color.GOLDENROD, true)));
        this.addToBot(new VFXAction(new MiracleEffect()));
        this.addToBot(new GainEnergyAction(this.reward));
        super.update();
    }
}
