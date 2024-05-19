package FrierenMod.cards.white;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RustCleanMagic extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(RustCleanMagic.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.SKILL, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);

    public RustCleanMagic() {
        super(info);
    }

//    public RustCleanMagic(CardColor color) {
//        super(ID, 1, CardType.SKILL, color, CardRarity.UNCOMMON, CardTarget.ENEMY);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.magicNumber = this.baseMagicNumber = 10;
        this.chantX = this.baseChantX = 1;
        this.isChantCard = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(-4);
        }
    }


    @Override
    public void triggerOnGlowCheck() {
        if (CombatHelper.getAllManaNum() > 10)
            this.glowColor = GOLD_BORDER_GLOW_COLOR;
        else
            this.glowColor = BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChantAction(this.chantX, new RemoveDebuffsAction(p)));
    }
}
