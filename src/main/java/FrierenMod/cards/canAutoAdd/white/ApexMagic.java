package FrierenMod.cards.canAutoAdd.white;

import FrierenMod.actions.ApexMagicAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.canAutoAdd.tempCards.Mana;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.ChantHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ApexMagic extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(ApexMagic.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.RARE, CardTarget.ALL_ENEMY);

    public ApexMagic() {
        super(info);
    }

//    public ApexMagic(CardColor color) {
//        super(ID, 0, CardType.ATTACK, color, CardRarity.RARE, CardTarget.ALL_ENEMY);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.magicNumber = this.baseMagicNumber = 1;
        this.cardsToPreview = new Mana();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.selfRetain = true;
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApexMagicAction(this.magicNumber));
    }

    public void triggerOnGlowCheck() {
        if (ChantHelper.getManaNumInDrawPile() == 4 && ChantHelper.getManaNumInHand() == 4 && ChantHelper.getManaNumInDiscardPile() == 4)
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        else {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }
}
