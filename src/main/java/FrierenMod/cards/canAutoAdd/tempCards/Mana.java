package FrierenMod.cards.canAutoAdd.tempCards;

import FrierenMod.actions.ManaAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Mana extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Mana.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -2, CardType.STATUS, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);

    public Mana() {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.isMana = true;
        this.exhaust = true;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ManaAction(Type.NORMAL));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return true;
    }
    public enum Type {
        NORMAL,
        ACCEL,
        LIMITED_OVER,
        LIMITED_OVER_ACCEL
    }
}
