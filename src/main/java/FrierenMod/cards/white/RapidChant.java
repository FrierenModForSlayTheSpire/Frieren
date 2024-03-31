package FrierenMod.cards.white;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RapidChant extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(RapidChant.class.getSimpleName());

    public RapidChant() {
        super(ID, 0, CardEnums.FRIEREN_CARD, CardRarity.COMMON);
    }

    public RapidChant(CardColor color) {
        super(ID, 0, color, CardRarity.COMMON);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.isChantCard = true;
        this.chantX = this.baseChantX = 3;
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

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChantAction(this.chantX));
    }
}
