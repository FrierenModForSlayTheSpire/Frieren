package FrierenMod.actions;

import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.gameHelpers.HardCodedPowerHelper;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;

public class MakeMagicPowerInDrawPileAction extends AbstractGameAction {
    private final AbstractCard cardToMake;
    private static final float x = Settings.WIDTH / 2.0F;
    private static final float y = Settings.HEIGHT / 2.0F;

    public MakeMagicPowerInDrawPileAction(int amount) {
        setValues(this.target, this.source, amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.startDuration = Settings.FAST_MODE ? Settings.ACTION_DUR_FAST : 0.5F;
        this.duration = this.startDuration;
        this.cardToMake = new MagicPower();
    }
    public void update() {
        if (this.duration == this.startDuration) {
            if(HardCodedPowerHelper.dontHaveBanMagicGainPower()){
                for (int i = 0; i < this.amount; i++) {
                    AbstractCard c = this.cardToMake.makeStatEquivalentCopy();
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(c, x, y, true, true, false));
                }
            }
            this.duration -= Gdx.graphics.getDeltaTime();
        }
        tickDuration();
    }
}
