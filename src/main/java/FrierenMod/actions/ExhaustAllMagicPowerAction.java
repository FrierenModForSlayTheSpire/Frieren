package FrierenMod.actions;

import FrierenMod.helpers.ChantHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

import static FrierenMod.tags.CustomTags.MAGIC_POWER;

public class ExhaustAllMagicPowerAction extends AbstractGameAction {
    private final float startingDuration;
    private final int exhaustNumberInDrawPile;
    private final int exhaustNumberInHand;
    private final int exhaustNumberInDiscardPile;

    public ExhaustAllMagicPowerAction() {
        this.actionType = ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        ChantHelper helper = new ChantHelper();
        this.exhaustNumberInDrawPile = helper.getMagicPowerNumInDiscardPile();
        this.exhaustNumberInHand = helper.getMagicPowerNumInHand();
        this.exhaustNumberInDiscardPile = helper.getMagicPowerNumInDiscardPile();
    }

    public void update() {
        if (this.duration == this.startingDuration) {
            this.addToBot(new ExhaustMagicPowerInDrawPileAction(this.exhaustNumberInDrawPile));
            this.addToBot(new ExhaustMagicPowerInHandAction(this.exhaustNumberInHand));
            this.addToBot(new ExhaustMagicPowerInDiscardPileAction(this.exhaustNumberInDiscardPile));
            this.isDone = true;
        }
    }
}
