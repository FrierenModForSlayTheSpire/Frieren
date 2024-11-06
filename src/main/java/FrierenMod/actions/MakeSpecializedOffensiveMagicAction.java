package FrierenMod.actions;

import FrierenMod.cardMods.DamageMod;
import FrierenMod.cardMods.ExhaustEtherealMod;
import FrierenMod.cards.tempCards.SpecializedOffensiveMagic;
import FrierenMod.powers.AbstractBasePower;
import FrierenMod.powers.FusionPower.AbstractFusionPower;
import FrierenMod.powers.FusionPower.DamageFusionPower;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MakeSpecializedOffensiveMagicAction extends AbstractGameAction {
    public int baseDamage;

    public MakeSpecializedOffensiveMagicAction(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    @Override
    public void update() {
        SpecializedOffensiveMagic magic = new SpecializedOffensiveMagic();
        magic.baseDamage = baseDamage;
        CardModifierManager.addModifier(magic, new DamageMod(baseDamage));
        for (AbstractPower po : AbstractDungeon.player.powers) {
            if (po instanceof AbstractFusionPower) {
                if (po instanceof DamageFusionPower)
                    magic.damage = magic.baseDamage += po.amount;
                else
                    CardModifierManager.addModifier(magic, ((AbstractFusionPower) po).modifier);
                this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, po));
            }
        }
        CardModifierManager.addModifier(magic, new ExhaustEtherealMod());
        for (AbstractPower po : AbstractDungeon.player.powers) {
            if (po instanceof AbstractBasePower) {
                ((AbstractBasePower) po).beforeGainSpecializedOffensiveMagic(magic);
            }
        }
        this.addToBot(new MakeTempCardInHandAction(magic));
        for (AbstractPower po : AbstractDungeon.player.powers) {
            if (po instanceof AbstractBasePower) {
                ((AbstractBasePower) po).afterGainSpecializedOffensiveMagic(magic);
            }
        }
        this.isDone = true;
    }
}
