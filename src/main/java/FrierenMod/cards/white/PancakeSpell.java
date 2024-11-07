package FrierenMod.cards.white;

import FrierenMod.actions.PancakeAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PancakeSpell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(PancakeSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON);

    public PancakeSpell() {
        super(info);
    }

//    public PancakeSpell(CardColor color) {
//        super(ID, 0, color, CardRarity.UNCOMMON);
//    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.cardsToPreview = new Mana();
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(-1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new PancakeAction(this.magicNumber));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (CombatHelper.getManaNumInHand() < this.magicNumber)
            return false;
        return super.canUseOriginally(p, m);
    }
}
