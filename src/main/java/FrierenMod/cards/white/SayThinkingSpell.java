package FrierenMod.cards.white;

import FrierenMod.actions.ExhaustManaInHandAction;
import FrierenMod.actions.PlayRandomCardAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.gameHelpers.ChantHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SayThinkingSpell extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(SayThinkingSpell.class.getSimpleName());
    public SayThinkingSpell() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = 1;
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
        int amt = ChantHelper.getMagicPowerNumInHand();
        this.addToBot(new ExhaustManaInHandAction(amt));
        for (int i = 0; i < amt * magicNumber; i++)
            addToBot(new PlayRandomCardAction(
                    (AbstractDungeon.getCurrRoom()).monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false));
    }
}
