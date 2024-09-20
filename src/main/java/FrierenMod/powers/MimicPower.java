package FrierenMod.powers;

import FrierenMod.actions.DestroySpecifiedCardAction;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;

public class MimicPower extends AbstractBasePower {
    public static final String POWER_ID = ModInformation.makeID(MimicPower.class.getSimpleName());

    public MimicPower(AbstractCreature owner, int amount) {
        super(POWER_ID, owner, amount, NeutralPowertypePatch.NEUTRAL);
        this.updateDescription();
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        flashWithoutSound();
        this.amount--;
        if (this.amount == 0) {
            playApplyPowerSfx();
            AbstractDungeon.actionManager.callEndTurnEarlySequence();
            AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.GOLD, true));
            this.addToBot(new RemoveSpecificPowerAction(owner, owner, this));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, owner, new DarkPower(AbstractDungeon.player, 0)));
            ActionHelper.addToBotAbstract(() -> {
                for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                    if (c instanceof Mana)
                        this.addToBot(new DestroySpecifiedCardAction(c, AbstractDungeon.player.drawPile));
                }
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c instanceof Mana)
                        this.addToBot(new DestroySpecifiedCardAction(c, AbstractDungeon.player.hand));
                }
                for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                    if (c instanceof Mana)
                        this.addToBot(new DestroySpecifiedCardAction(c, AbstractDungeon.player.discardPile));
                }
            });
        }
        updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], this.amount);
    }
}