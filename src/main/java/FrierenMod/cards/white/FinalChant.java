package FrierenMod.cards.white;

import FrierenMod.actions.FinalChantAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.ChantHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FinalChant extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(FinalChant.class.getSimpleName());

    public FinalChant() {
        super(ID, 2, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON);
    }

    public FinalChant(CardColor color) {
        super(ID, 2, color, CardRarity.UNCOMMON);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.isChantCard = true;
        this.block = this.baseBlock;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.chantX = this.baseChantX = ChantHelper.getAllManaNum();
        this.addToBot(new FinalChantAction(this));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return super.canUseOriginally(p, m);
    }
}
