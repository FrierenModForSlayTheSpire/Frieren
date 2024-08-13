package FrierenMod.cards.white;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FinalChant extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(FinalChant.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON);

    public FinalChant() {
        super(info);
    }

//    public FinalChant(CardColor color) {
//        super(ID, 2, color, CardRarity.UNCOMMON);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.tags.add(AbstractBaseCard.Enum.CHANT);
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
        this.chantX = this.baseChantX = CombatHelper.getAllManaNum();
        this.addToBot(new ChantAction(ChantAction.ChantType.FINAL));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return super.canUseOriginally(p, m);
    }
}
