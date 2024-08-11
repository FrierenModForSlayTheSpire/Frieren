package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.gameHelpers.CardPoolHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class TheLivingLegend extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(TheLivingLegend.class.getSimpleName());

    public TheLivingLegend() {
        super(ID);
        loadRarity(MagicItemRarity.RARE);
    }

    @Override
    public void takeEffect() {
        for (int i = 0; i < secondMagicNumber; i++) {
            AbstractCard c = CardPoolHelper.getRandomCard(CardPoolHelper.PoolType.LEGENDARY_SPELL);
            switch (this.currentSlot){
                case 0:
                    addToBot(new MakeTempCardInDrawPileAction(c,1,true,true));
                    break;
                case 1:
                    addToBot(new MakeTempCardInHandAction(c));
                    break;
                case 2:
                    addToBot(new MakeTempCardInDiscardAction(c,1));
                    break;
                default:
                    break;
            }
        }
    }
}
