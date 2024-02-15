package FrierenMod.actions;

import FrierenMod.gameHelpers.HardCodedPowerHelper;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

public class ChantFromDiscardPileAction extends AbstractGameAction {
    private final int magicNumber;
    public ChantFromDiscardPileAction(int magicNumber){
        this.magicNumber = magicNumber;
    }
    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new VFXAction(new BorderLongFlashEffect(Color.FIREBRICK, true)));
        this.addToBot(new VFXAction(p, new InflameEffect(p), 1.0F));
        if(!HardCodedPowerHelper.hasChantWithoutMagicPower()){
            this.addToBot(new ExhaustMagicPowerInDiscardPileAction(this.magicNumber));
        }
        this.addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,this.magicNumber)));
        this.isDone = true;
    }
}
