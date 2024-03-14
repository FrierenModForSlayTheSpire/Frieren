package FrierenMod.cards.white.chant;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.BlueMoonWeed;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FlowerFieldSpell extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(FlowerFieldSpell.class.getSimpleName());
    public FlowerFieldSpell() {
        super(ID, 1, CardRarity.COMMON);
        this.isChantCard = true;
        this.chantX = this.baseChantX =3;
        BlueMoonWeed c = new BlueMoonWeed();
        if(this.upgraded){
            c.upgrade();
        }
        this.cardsToPreview = c;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        BlueMoonWeed c = new BlueMoonWeed();
        if(this.upgraded){
            c.upgrade();
        }
        this.addToBot(new ChantAction(this.chantX,new MakeTempCardInHandAction(c,1)));
    }
}
