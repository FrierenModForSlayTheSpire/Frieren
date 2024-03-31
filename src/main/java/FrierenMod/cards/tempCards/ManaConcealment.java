package FrierenMod.cards.tempCards;

import FrierenMod.actions.HideMagicAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ManaConcealment extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(ManaConcealment.class.getSimpleName());
    public ManaConcealment() {
        super(ID, 0, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.cardsToPreview = new Mana();
        this.exhaust = true;
        this.selfRetain = true;
    }
    @Override
    public boolean canUpgrade() {
        return false;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
       this.addToBot(new HideMagicAction());
    }
}
