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

import static FrierenMod.gameHelpers.HardCodedPowerHelper.CHANT_WITHOUT_MAGIC;

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
        if(!p.hasPower(CHANT_WITHOUT_MAGIC)){
            this.addToTop(new ExhaustManaInHandAction(this.magicNumber));
        }
        this.addToBot(new VFXAction(new BorderFlashEffect(Color.GOLDENROD, true)));
        this.addToBot(new VFXAction(new MiracleEffect()));
        this.addToBot(new GainEnergyAction(this.magicNumber));
        this.isDone = true;
    }
}
