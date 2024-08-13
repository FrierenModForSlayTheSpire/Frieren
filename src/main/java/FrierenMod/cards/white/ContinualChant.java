package FrierenMod.cards.white;

import FrierenMod.actions.ChantAction;
import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.powers.BanManaGainPower;
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
        this.chantX = this.baseChantX = 3;
        this.magicNumber = this.baseMagicNumber = 3;
        this.cardsToPreview = new Mana();
        this.tags.add(AbstractBaseCard.Enum.CHANT);
        this.tags.add(AbstractBaseCard.Enum.LEGENDARY_SPELL);
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
        if (p.hasPower(BanManaGainPower.POWER_ID))
            return super.canUse(p, m);
        return super.canUseOriginally(p, m);
    }
}
