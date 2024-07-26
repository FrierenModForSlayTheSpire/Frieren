package FrierenMod.cards.optionCards.magicFactors;

import FrierenMod.actions.ExhaustManaInHandAction;
import FrierenMod.utils.FrierenRes;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

public class BaseFactorBeta extends AbstractMagicFactor {
    public static final String ID = ModInformation.makeID(BaseFactorBeta.class.getSimpleName());

    public BaseFactorBeta() {
        super(ID);
        this.factorRarity = FactorRarityType.BASIC;
    }

    @Override
    public void takeEffect() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new VFXAction(p, new VerticalAuraEffect(FrierenRes.RENDER_COLOR, p.hb.cX, p.hb.cY), 0.1F));
        addToBot(new VFXAction(p, new VerticalAuraEffect(Color.PURPLE, p.hb.cX, p.hb.cY), 0.05F));
        if (this.magicNumber > 0)
            this.addToBot(new ExhaustManaInHandAction(this.magicNumber));
        this.addToBot(new GainEnergyAction(this.secondMagicNumber));
    }
}
