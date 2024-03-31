package FrierenMod.cards.canAutoAdd.tempCards;

import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Dark extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(Dark.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    public Dark() {
        super(info);
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
        this.isEthereal = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(this.magicNumber));
    }
}
