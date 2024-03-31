package FrierenMod.cards.white;

import FrierenMod.actions.MakeManaInHandAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GrapeSpell extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(GrapeSpell.class.getSimpleName());

    public GrapeSpell() {
        super(ID, 1, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON);
    }

    public GrapeSpell(CardColor color) {
        super(ID, 1, color, CardRarity.UNCOMMON);
    }

    @Override
    public void initSpecifiedAttributes() {
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
        this.addToBot(new ExhaustAction(2, false, false, false));
        this.addToBot(new MakeManaInHandAction(2));
    }
}
