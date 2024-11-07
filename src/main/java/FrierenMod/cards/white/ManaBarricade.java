package FrierenMod.cards.white;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ManaBarricade extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ManaBarricade.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.SKILL, CardEnums.FRIEREN_CARD, CardRarity.COMMON);

    public ManaBarricade() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.block = this.baseBlock = 12;
        this.chantX = this.baseChantX = 2;
        this.tags.add(AbstractBaseCard.Enum.CHANT);
    }
    //    public ManaBarricade(CardColor color) {
//        super(ID, 2, CardType.POWER, color, CardRarity.RARE);
//    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChantAction(this.chantX, this.block));
    }
}
