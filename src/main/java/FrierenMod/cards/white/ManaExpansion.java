package FrierenMod.cards.white;

import FrierenMod.actions.MakeManaInDiscardAction;
import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.actions.MakeManaInHandAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ManaExpansion extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ManaExpansion.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardEnums.FRIEREN_CARD, CardRarity.RARE);

    public ManaExpansion() {
        super(info);
    }


    @Override
    public void initSpecifiedAttributes() {
        this.magicNumber = baseMagicNumber = 1;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ActionHelper.addToBotAbstract(() -> {
            int draw = CombatHelper.getManaNumInDrawPile();
            int hand = CombatHelper.getManaNumInHand();
            int discard = CombatHelper.getManaNumInDiscardPile();
            if (draw > 0)
                this.addToBot(new MakeManaInDrawPileAction(draw * this.magicNumber));
            if (hand > 0)
                this.addToBot(new MakeManaInHandAction(hand * this.magicNumber));
            if (discard > 0)
                this.addToBot(new MakeManaInDiscardAction(discard * this.magicNumber));
        });
    }
}
