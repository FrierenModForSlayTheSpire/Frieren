package FrierenMod.actions;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.gameHelpers.SlotBgHelper;
import FrierenMod.patches.fields.CardRewardScreenField;
import FrierenMod.ui.slot.Slot;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class ChantOneAction extends AbstractGameAction {
    private final ArrayList<AbstractCard> choices;
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ModInformation.makeID(ChantOneAction.class.getSimpleName())).TEXT;

    public ChantOneAction(ArrayList<AbstractCard> choices) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.choices = choices;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            this.choices.sort((c1, c2) -> {
                if (c1 instanceof AbstractMagicItem && c2 instanceof AbstractMagicItem)
                    return ((AbstractMagicItem) c1).currentSlot - ((AbstractMagicItem) c2).currentSlot;
                return 0;
            });
            AbstractDungeon.cardRewardScreen.chooseOneOpen(choices);
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.dynamicBanner.appear(TEXT[0]);
            ArrayList<Slot> slots = SlotBgHelper.getLoadingSlots();
            CardRewardScreenField.getSlots().clear();
            for (int i = 0; i < 3; i++) {
                slots.get(i).setPosition(AbstractDungeon.cardRewardScreen.rewardGroup.get(i).current_x, AbstractDungeon.cardRewardScreen.rewardGroup.get(i).current_y);
                CardRewardScreenField.getSlots().add(slots.get(i));
            }
            this.tickDuration();
        } else {
            this.tickDuration();
        }
    }
}

