package FrierenMod.cards.white;

import FrierenMod.actions.SortDrawPileAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BreaksBarriersSpell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(BreaksBarriersSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardEnums.FRIEREN_CARD, CardRarity.RARE);

    public BreaksBarriersSpell() {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SortDrawPileAction());
        this.addToBot(new DrawCardAction(magicNumber));
    }
}
