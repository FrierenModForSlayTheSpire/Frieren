package FrierenMod.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

import static FrierenMod.gameHelpers.HardCodedPowerHelper.CHANT_WITHOUT_MAGIC;

public class ChantFromDiscardPileAction extends ChantFromCardGroupAction {
    private final int magicNumber;
    public ChantFromDiscardPileAction(int magicNumber){
        this.magicNumber = magicNumber;
        this.haveNotTriggered = true;
    }
    public ChantFromDiscardPileAction(int magicNumber,boolean haveNotTriggered){
        this.magicNumber = magicNumber;
        this.haveNotTriggered = haveNotTriggered;
    }
    public ChantFromDiscardPileAction(int magicNumber, AbstractGameAction... nextAction){
        this.magicNumber = magicNumber;
        this.nextAction = nextAction;
        this.haveNotTriggered = true;
    }
    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new VFXAction(new BorderLongFlashEffect(Color.FIREBRICK, true)));
        this.addToBot(new VFXAction(p, new InflameEffect(p), 1.0F));
        if(!p.hasPower(CHANT_WITHOUT_MAGIC)){
            this.addToBot(new ExhaustManaInDiscardPileAction(this.magicNumber));
        }
        this.addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,this.magicNumber)));
        this.triggerPowers();
        this.triggerCards();
        this.addNextAction();
        this.isDone = true;
    }
}
