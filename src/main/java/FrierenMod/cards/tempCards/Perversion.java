package FrierenMod.cards.tempCards;

import FrierenMod.actions.ExchangeDrawAndDiscardPileAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Perversion extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Perversion.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -2, CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);

    public Perversion() {
        super(info);
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c instanceof AbstractBaseCard && ((AbstractBaseCard) c).isMana) {
            this.addToBot(new ExchangeDrawAndDiscardPileAction(AbstractDungeon.player));
        }
    }
}
