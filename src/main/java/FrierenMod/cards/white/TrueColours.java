package FrierenMod.cards.white;

import FrierenMod.actions.ChantAction;
import FrierenMod.actions.MakeManaInDiscardAction;
import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.actions.MakeManaInHandAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TrueColours extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(TrueColours.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardEnums.FRIEREN_CARD, CardRarity.RARE);

    public TrueColours() {
        super(info);
    }

//    public TrueColours(CardColor color) {
//        super(ID, 1, CardEnums.FRIEREN_CARD, CardRarity.RARE);
//    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.chantX = this.baseChantX = 4;
        this.tags.add(AbstractBaseCard.Enum.CHANT);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChantAction(this.chantX, new DrawCardAction(3), new MakeManaInHandAction(3)));
        this.addToBot(new GainEnergyAction(3));
        this.addToBot(new MakeManaInDrawPileAction(3));
        this.addToBot(new MakeManaInDiscardAction(3));
    }
}
