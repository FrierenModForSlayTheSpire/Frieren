package FrierenMod.cards.canAutoAdd.tempCards;

import FrierenMod.actions.HideManaAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ManaConcealment extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ManaConcealment.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    public ManaConcealment() {
        super(info);
        this.cardsToPreview = new Mana();
        this.exhaust = true;
        this.selfRetain = true;
    }
    @Override
    public boolean canUpgrade() {
        return false;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
       this.addToBot(new HideManaAction());
    }
}
