package FrierenMod.cards.white;

import FrierenMod.actions.ExhaustMagicPowerInHandAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.MagicPower;
import FrierenMod.gameHelpers.ChantHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.BetterDrawPileToHandAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PancakeMagic extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(PancakeMagic.class.getSimpleName());
    public PancakeMagic() {
        super(ID, 0, CardRarity.UNCOMMON);
        this.cardsToPreview = new MagicPower();
        this.magicNumber = this.baseMagicNumber = 1;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ExhaustMagicPowerInHandAction(1));
        this.addToBot(new MakeTempCardInDrawPileAction(this.cardsToPreview.makeCopy(),1,true,true));
        this.addToBot(new BetterDrawPileToHandAction(1));
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!new ChantHelper().canChantFromHand(1)){
            return false;
        }
        else {
            return super.upgradedCanUse(p,m);
        }
    }
}
