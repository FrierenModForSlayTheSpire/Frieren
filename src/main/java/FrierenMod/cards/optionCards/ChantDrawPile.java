package FrierenMod.cards.optionCards;

import FrierenMod.actions.ChantFromDrawPileAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChantDrawPile extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ChantDrawPile.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -2, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    public AbstractGameAction[] nextAction;

    public ChantDrawPile() {
        super(info);
        this.secondMagicNumber = this.baseSecondMagicNumber = 0;
        this.block = this.baseBlock = 0;
        this.cardsToPreview = new Mana();
    }
    public ChantDrawPile(int manaExhaust, int reward) {
        super(info);
        this.secondMagicNumber = this.baseSecondMagicNumber = manaExhaust;
        this.block = this.baseBlock = reward;
        this.isSecondMagicNumberModified = (manaExhaust < reward);
        this.nextAction = null;
    }
    public ChantDrawPile(int manaExhaust, int reward, AbstractGameAction... nexAction) {
        super(info);
        this.secondMagicNumber = this.baseSecondMagicNumber = manaExhaust;
        this.block = this.baseBlock = reward;
        this.isSecondMagicNumberModified = (manaExhaust < reward);
        this.nextAction = nexAction;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    public void onChoseThisOption() {
        this.applyPowers();
        this.addToBot(new ChantFromDrawPileAction(this.secondMagicNumber, this.block, this.nextAction));
    }
}
