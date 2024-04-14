package FrierenMod.cards.white;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LureTheEnemyInDeep extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(LureTheEnemyInDeep.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.SKILL, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.NONE);

    public LureTheEnemyInDeep() {
        super(info);
    }

//    public LureTheEnemyInDeep(CardColor color) {
//        super(ID, 1, CardType.SKILL, color, CardRarity.UNCOMMON, CardTarget.NONE);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.isChantCard = true;
        this.chantX = this.baseChantX = 1;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeChantX(1);
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChantAction(this.chantX, this, new DrawCardAction(this.magicNumber)));
    }

    @Override
    public void applyPowers() {
        this.returnToHand = false;
        super.applyPowers();
    }
}
