package FrierenMod.actions;

import FrierenMod.cards.tempCards.Mana;
import FrierenMod.powers.BanManaGainPower;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;

public class MakeManaInDrawPileAction extends AbstractGameAction {
    private final AbstractCard cardToMake;
    private static final float x = Settings.WIDTH / 2.0F;
    private static final float y = Settings.HEIGHT / 2.0F;
    private boolean ignoreBanPower;

    public MakeManaInDrawPileAction(int amount) {
        setValues(this.target, this.source, amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.startDuration = Settings.FAST_MODE ? Settings.ACTION_DUR_FAST : 0.5F;
        this.duration = this.startDuration;
        this.cardToMake = new Mana();
    }

    public MakeManaInDrawPileAction(int amount, boolean ignoreBanPower) {
        this(amount);
        this.ignoreBanPower = true;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            AbstractPlayer p = AbstractDungeon.player;
            if (!p.hasPower(BanManaGainPower.POWER_ID) || ignoreBanPower) {
                for (int i = 0; i < this.amount; i++) {
                    AbstractCard c = this.cardToMake.makeStatEquivalentCopy();
                    ShowCardAndAddToDrawPileEffect effect = new ShowCardAndAddToDrawPileEffect(c, x, y, true, true, false);
                    effect.duration = 0.5F;
                    AbstractDungeon.effectList.add(effect);
                }
            }
            this.duration -= Gdx.graphics.getDeltaTime();
        }
        tickDuration();
    }
}
