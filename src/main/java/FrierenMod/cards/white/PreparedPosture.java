package FrierenMod.cards.white;

import FrierenMod.actions.DrawChantAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PreparedPosture extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(PreparedPosture.class.getSimpleName());

    public PreparedPosture() {
        super(ID, 1, CardEnums.FRIEREN_CARD, CardRarity.COMMON);
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
        this.addToBot(new DrawChantAction(1));
    }
}
