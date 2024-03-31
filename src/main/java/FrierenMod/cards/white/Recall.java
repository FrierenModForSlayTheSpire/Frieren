package FrierenMod.cards.white;

import FrierenMod.actions.RecallAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Recall extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(Recall.class.getSimpleName());
    public Recall() {
        super(ID, 1, CardRarity.RARE);
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust=true;
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
        this.addToBot(new RecallAction(this.magicNumber));
    }
}
