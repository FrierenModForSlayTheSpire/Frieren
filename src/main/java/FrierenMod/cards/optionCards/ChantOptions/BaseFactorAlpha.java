package FrierenMod.cards.optionCards.ChantOptions;

import FrierenMod.actions.ExhaustManaInDrawPileAction;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.utils.FrierenRes;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

public class BaseFactorAlpha extends AbstractMagicFactor {
    public static final String ID = ModInformation.makeID(BaseFactorAlpha.class.getSimpleName());

    public BaseFactorAlpha() {
        super(ID);
    }

    @Override
    public void takeEffect() {
        ActionHelper.addToBotAbstract(()->{
            AbstractPlayer p = AbstractDungeon.player;
            addToBot(new VFXAction(p, new VerticalAuraEffect(FrierenRes.RENDER_COLOR, p.hb.cX, p.hb.cY), 0.1F));
            addToBot(new VFXAction(p, new VerticalAuraEffect(Color.PURPLE, p.hb.cX, p.hb.cY), 0.05F));
            if (this.magicNumber > 0)
                this.addToBot(new ExhaustManaInDrawPileAction(this.magicNumber));
            for (int i = 0; i < 2; i++) {
                this.addToBot(new GainBlockAction(p, p, this.secondMagicNumber));
            }
        });
    }
}
