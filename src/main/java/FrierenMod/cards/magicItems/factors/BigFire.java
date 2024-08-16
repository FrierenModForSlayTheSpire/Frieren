package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.cards.white.LittleFire;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class BigFire extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(BigFire.class.getSimpleName());

    public BigFire() {
        super(ID);
        loadRarity(MagicItemRarity.UNCOMMON);
        this.manaNeedMultipleCoefficient = 2;
        this.cardsToPreview = new LittleFire();
    }

    @Override
    public void takeEffect() {
        for (int i = 0; i < secondMagicNumber; i++) {
            AbstractCard c = new LittleFire();
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
