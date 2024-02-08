package FrierenMod.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;

public class ChantFromDiscardPileAction extends AbstractGameAction {
    private final int magicNumber;
    private final boolean upgraded;
    public ChantFromDiscardPileAction(int magicNumber){
        this.magicNumber = magicNumber;
        this.upgraded = false;
    }
    public ChantFromDiscardPileAction(boolean upgraded,int magicNumber){
        this.magicNumber = magicNumber;
        this.upgraded = upgraded;
    }
    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new VFXAction(new BorderLongFlashEffect(Color.FIREBRICK, true)));
        this.addToBot(new VFXAction(p, new InflameEffect(p), 1.0F));
        if(!this.upgraded){
            this.addToBot(new ExhaustMagicPowerInDiscardPileAction(this.magicNumber));
        }
        this.addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,this.magicNumber)));
        this.isDone = true;
    }
}
