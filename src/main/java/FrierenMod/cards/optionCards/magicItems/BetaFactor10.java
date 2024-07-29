package FrierenMod.cards.optionCards.magicItems;

import FrierenMod.gameHelpers.CardPoolHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class BetaFactor10 extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BetaFactor10.class.getSimpleName());

    public BetaFactor10() {
        super(ID);
        this.magicItemRarity = MagicItemRarity.RARE;
        this.manaNeedMultipleCoefficient = 2;
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
