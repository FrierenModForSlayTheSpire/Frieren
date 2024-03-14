package FrierenMod.cards.tempCards;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Repent extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(Repent.class.getSimpleName());
    public Repent() {
        super(ID, 0, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = true;
        this.retain =true;
        this.baseMagicNumber = this.magicNumber = 2;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new BetterDiscardPileToHandAction(this.magicNumber,0));
    }
}