package FrierenMod.cards.tempCards;

import FrierenMod.actions.ExchangeDrawAndDiscardPileAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class UpsideDown extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(UpsideDown.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -2, CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);

    public UpsideDown() {
        super(info);
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c instanceof Mana) {
            this.addToBot(new ExchangeDrawAndDiscardPileAction(AbstractDungeon.player));
            this.addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, 2));
        }
    }
}
