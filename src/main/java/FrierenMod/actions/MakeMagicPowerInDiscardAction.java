package FrierenMod.actions;

import FrierenMod.cards.tempCards.MagicPower;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;

import static FrierenMod.gameHelpers.HardCodedPowerHelper.BAN_MAGIC_GAIN;

public class MakeMagicPowerInDiscardAction extends AbstractGameAction {
    private final AbstractCard c;
    private final int numCards;
    public MakeMagicPowerInDiscardAction(int amount) {
        this.numCards = amount;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.startDuration = Settings.FAST_MODE ? Settings.ACTION_DUR_FAST : 0.5F;
        this.duration = this.startDuration;
        this.c = new MagicPower();
    }

    public void update() {
        if (this.duration == this.startDuration) {
            AbstractPlayer p = AbstractDungeon.player;
            if(!p.hasPower(BAN_MAGIC_GAIN)){
                for(int i = 0; i < this.numCards; ++i) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(c.makeStatEquivalentCopy()));
                }
            }
            this.duration -= Gdx.graphics.getDeltaTime();
        }
        this.tickDuration();
    }
}
