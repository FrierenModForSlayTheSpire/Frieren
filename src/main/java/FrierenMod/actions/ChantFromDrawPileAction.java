package FrierenMod.actions;

import FrierenMod.powers.ChantWithoutManaPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

public class ChantFromDrawPileAction extends ChantFromCardGroupAction {
    private final int block;
    private final int magicNumber;
    public ChantFromDrawPileAction(int block, int magicNumber){
        this.block = block;
        this.magicNumber = magicNumber;
        this.haveNotTriggered = true;
    }
    public ChantFromDrawPileAction(int block, int magicNumber,boolean haveNotTriggered){
        this.block = block;
        this.magicNumber = magicNumber;
        this.haveNotTriggered = haveNotTriggered;
    }
    public ChantFromDrawPileAction(int block, int magicNumber, AbstractGameAction... nextAction){
        this.block = block;
        this.magicNumber = magicNumber;
        this.nextAction = nextAction;
        this.haveNotTriggered = true;
    }
    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new VFXAction(new BorderLongFlashEffect(Color.FIREBRICK, true)));
        this.addToBot(new VFXAction(p, new InflameEffect(p), 1.0F));
        if(!p.hasPower(ChantWithoutManaPower.POWER_ID)){
            this.addToBot(new ExhaustManaInDrawPileAction(this.magicNumber));
        }
        for (int i = 0; i < 2; i++) {
            this.addToBot(new GainBlockAction(p,p,this.block));
        }
        if(haveNotTriggered){
            this.triggerPowers();
            this.triggerCards();
            this.addNextAction();
        }
        this.isDone = true;
    }
}
