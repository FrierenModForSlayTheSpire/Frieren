package FrierenMod.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;

public class ChantFromHandAction extends AbstractGameAction {
    private final int magicNumber;
    public ChantFromHandAction(int magicNumber){
        this.magicNumber = magicNumber;
    }
    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new VFXAction(new BorderLongFlashEffect(Color.FIREBRICK, true)));
        this.addToBot(new VFXAction(p, new InflameEffect(p), 1.0F));
        this.addToBot(new ExhaustMagicPowerInHandAction(this.magicNumber));
        if (!Settings.DISABLE_EFFECTS) {
            this.addToBot(new VFXAction(new BorderFlashEffect(Color.GOLDENROD, true)));
        }
        this.addToBot(new VFXAction(new MiracleEffect()));
        this.addToBot(new GainEnergyAction(this.magicNumber));
        this.isDone = true;
    }
}