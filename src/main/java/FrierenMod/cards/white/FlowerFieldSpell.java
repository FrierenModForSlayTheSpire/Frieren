package FrierenMod.cards.white;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.cards.canAutoAdd.tempCards.BlueMoonWeed;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FlowerFieldSpell extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(FlowerFieldSpell.class.getSimpleName());

    public FlowerFieldSpell() {
        super(ID, 1, CardEnums.FRIEREN_CARD, CardRarity.COMMON);
    }

    public FlowerFieldSpell(CardColor color) {
        super(ID, 1, color, CardRarity.COMMON);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.isChantCard = true;
        this.chantX = this.baseChantX = 3;
        this.cardsToPreview = new BlueMoonWeed();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.cardsToPreview.upgrade();
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        BlueMoonWeed c = new BlueMoonWeed();
        if (this.upgraded) {
            c.upgrade();
        }
        this.addToBot(new ChantAction(this.chantX, new MakeTempCardInHandAction(c, 1)));
    }
}
