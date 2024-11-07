package FrierenMod.cards.white;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LureTheEnemyInDeep extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(LureTheEnemyInDeep.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardType.SKILL, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.NONE);

    public LureTheEnemyInDeep() {
        super(info);
    }

//    public LureTheEnemyInDeep(CardColor color) {
//        super(ID, 1, CardType.SKILL, color, CardRarity.UNCOMMON, CardTarget.NONE);
//    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.tags.add(AbstractBaseCard.Enum.CHANT);
        this.chantX = this.baseChantX = 2;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeChantX(-1);
            this.upgradeBaseCost(1);
            this.upgradeMagicNumber(-1);
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChantAction(this.chantX, this));
        this.addToBot(new GainEnergyAction(this.magicNumber));
    }

    @Override
    public void applyPowers() {
        this.returnToHand = false;
        super.applyPowers();
    }
}
