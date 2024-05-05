package FrierenMod.cards.white;

import FrierenMod.actions.ChantAction;
import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ContinualChant extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ContinualChant.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON);

    public ContinualChant() {
        super(info);
    }

    //    public ContinualChant(CardColor color) {
//        super(ID, 0, color, CardRarity.UNCOMMON);
//    }
    @Override
    public void initSpecifiedAttributes() {
        this.chantX = this.baseChantX = 4;
        this.magicNumber = this.baseMagicNumber = 4;
        this.cardsToPreview = new Mana();
        this.isChantCard = true;
        this.isLegendarySpell = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.upgradeChantX(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeManaInDrawPileAction(this.magicNumber));
        this.addToBot(new ChantAction(this.chantX));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (CombatHelper.cannotPlayLegendarySpell()) {
            this.cantUseMessage = cantUseTEXT[1];
            return false;
        }
        return super.canUseOriginally(p, m);
    }
}
