package FrierenMod.cards.white;

import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Recycle extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Recycle.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.SKILL, CardEnums.FRIEREN_CARD, CardRarity.COMMON, CardTarget.NONE);

    public Recycle() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
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
        ActionHelper.addToBotAbstract(() -> {
            int discard = CombatHelper.getManaNumInDiscardPile();
            if (discard > 0) {
                this.addToBot(new MakeManaInDrawPileAction(discard));
            }
        });
    }
}
