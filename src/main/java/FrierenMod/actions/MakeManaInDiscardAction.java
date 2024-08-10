package FrierenMod.actions;

import FrierenMod.cards.tempCards.Mana;
import FrierenMod.powers.BanManaGainPower;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;

public class MakeManaInDiscardAction extends AbstractGameAction {
    private final AbstractCard c;
    private final int numCards;
    private boolean ignoreBanPower;

    public MakeManaInDiscardAction(int amount) {
        this.numCards = amount;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.startDuration = Settings.FAST_MODE ? Settings.ACTION_DUR_FAST : 0.5F;
        this.duration = this.startDuration;
        this.c = new Mana();
    }

    public MakeManaInDiscardAction(int amount, boolean ignoreBanPower) {
        this(amount);
        this.ignoreBanPower = ignoreBanPower;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            AbstractPlayer p = AbstractDungeon.player;
            if (!p.hasPower(BanManaGainPower.POWER_ID) || ignoreBanPower) {
                for (int i = 0; i < this.numCards; ++i) {
                    ShowCardAndAddToDiscardEffect effect = new ShowCardAndAddToDiscardEffect(c.makeStatEquivalentCopy());
                    effect.duration = 0.5F;
                    AbstractDungeon.effectList.add(effect);
                }
            }
            this.duration -= Gdx.graphics.getDeltaTime();
        }
        this.tickDuration();
    }
}
