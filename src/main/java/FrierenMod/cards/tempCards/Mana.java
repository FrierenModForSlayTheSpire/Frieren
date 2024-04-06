package FrierenMod.cards.tempCards;

import FrierenMod.actions.ManaAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Mana extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(Mana.class.getSimpleName());

    public Mana() {
        super(ID, -2, CardType.STATUS, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.isMana = true;
        this.exhaust = true;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ManaAction(1));
    }
}
