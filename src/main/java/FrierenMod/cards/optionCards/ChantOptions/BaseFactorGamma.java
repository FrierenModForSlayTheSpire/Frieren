package FrierenMod.cards.optionCards.ChantOptions;

import FrierenMod.actions.ExhaustManaInDiscardPileAction;
import FrierenMod.utils.FrierenRes;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

public class BaseFactorGamma extends AbstractMagicFactor {
    public static final String ID = ModInformation.makeID(BaseFactorGamma.class.getSimpleName());

    public BaseFactorGamma() {
        super(ID);
    }

    @Override
    public void takeEffect() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new VFXAction(p, new VerticalAuraEffect(FrierenRes.RENDER_COLOR, p.hb.cX, p.hb.cY), 0.1F));
        addToBot(new VFXAction(p, new VerticalAuraEffect(Color.PURPLE, p.hb.cX, p.hb.cY), 0.05F));
        if (this.magicNumber > 0) {
            this.addToBot(new ExhaustManaInDiscardPileAction(this.magicNumber));
        }
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.secondMagicNumber)));
    }
}
