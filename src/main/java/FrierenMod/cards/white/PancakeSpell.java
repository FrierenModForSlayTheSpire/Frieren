package FrierenMod.cards.white;

import FrierenMod.actions.PancakeAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.gameHelpers.ChantHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PancakeSpell extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(PancakeSpell.class.getSimpleName());
    public PancakeSpell() {
        super(ID, 0, CardRarity.UNCOMMON);
        this.cardsToPreview = new Mana();
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
        this.addToBot(new PancakeAction(this.magicNumber));
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!ChantHelper.canChantFromHand(1)){
            return false;
        }
        else {
            return super.canUseOriginally(p,m);
        }
    }
}
