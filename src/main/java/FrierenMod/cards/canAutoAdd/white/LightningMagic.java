package FrierenMod.cards.canAutoAdd.white;

import FrierenMod.actions.LightningMagicAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LightningMagic extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(LightningMagic.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 3, CardEnums.FRIEREN_CARD, CardRarity.RARE);

    public LightningMagic() {
        super(info);
    }

//    public LightningMagic(CardColor color) {
//        super(ID, 3, color, CardRarity.RARE);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.isLegendarySpell = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new LightningMagicAction());
    }
}
