package FrierenMod.actions;

import FrierenMod.utils.FrierenRes;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

public class ChantFromDiscardPileAction extends ChantFromCardGroupAction {
    public ChantFromDiscardPileAction(int manaExhaust, int reward, boolean haveNotTriggered) {
        super(manaExhaust, reward);
        this.haveNotTriggered = haveNotTriggered;
    }

    public ChantFromDiscardPileAction(int manaExhaust, int reward, AbstractGameAction... nextAction) {
        super(manaExhaust, reward);
        this.nextAction = nextAction;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new VFXAction(p, new VerticalAuraEffect(FrierenRes.RENDER_COLOR, p.hb.cX, p.hb.cY), 0.1F));
        addToBot(new VFXAction(p, new VerticalAuraEffect(Color.PURPLE, p.hb.cX, p.hb.cY), 0.05F));
        if (this.manaExhaust > 0) {
            this.addToBot(new ExhaustManaInDiscardPileAction(this.manaExhaust));
        }
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.reward)));
        super.update();
    }
}
