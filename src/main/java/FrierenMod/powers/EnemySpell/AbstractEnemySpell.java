package FrierenMod.powers.EnemySpell;

import FrierenMod.powers.SpellCasterPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

public abstract class AbstractEnemySpell {
    public static final String[] descriptions = CardCrawlGame.languagePack.getPowerStrings(SpellCasterPower.POWER_ID).DESCRIPTIONS;
    public final AbstractCreature owner;
    public AbstractCreature target;
    public boolean isDone;

    protected AbstractEnemySpell(AbstractCreature owner, AbstractCreature target) {
        this.owner = owner;
        this.target = target;
        this.isDone = false;
    }

    protected AbstractEnemySpell(AbstractCreature owner) {
        this.owner = owner;
        this.isDone = false;
    }

    public void addToBot(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }

    public static String getSpellSignal(boolean finished) {
        return finished ? " #g" : " #r";
    }

    public void takeEffect(){
        if(!this.isDone){
            addToBot(new VFXAction(owner, new VerticalAuraEffect(Color.BLACK, owner.hb.cX, owner.hb.cY), 0.33F));
            addToBot(new VFXAction(owner, new VerticalAuraEffect(Color.PURPLE, owner.hb.cX, owner.hb.cY), 0.33F));
            addToBot(new VFXAction(owner, new VerticalAuraEffect(Color.CYAN, owner.hb.cX, owner.hb.cY), 0.0F));
            this.update();
            this.isDone = true;
        }
    }
    public abstract void update();

    public abstract String getDescription();
    public abstract int getManaNeed();
}
