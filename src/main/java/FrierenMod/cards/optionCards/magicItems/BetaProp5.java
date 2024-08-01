package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.effects.ExhaustMagicItemEffect;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class BetaProp5 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaProp5.class.getSimpleName());

    public BetaProp5() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.PROP;
        this.propCanChooseMaxAmt = 2;
    }

    public boolean propTakeEffect(ArrayList<AbstractCard> chosenCards) {
        if(!CombatHelper.isInCombat())
            return false;
        if (chosenCards.size() != propCanChooseMaxAmt)
            return false;
        for (AbstractCard c : chosenCards) {
            if (!(c instanceof AbstractMagicItem))
                return false;
            if (c == this)
                return false;
            if (((AbstractMagicItem) c).currentSlot > -1)
                return false;
        }
        for(AbstractCard c: chosenCards)
            AbstractDungeon.topLevelEffects.add(new ExhaustMagicItemEffect(c));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(2));
        return true;
    }
}
