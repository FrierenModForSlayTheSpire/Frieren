package FrierenMod.cards.white;

import FrierenMod.actions.ExhaustManaInCardGroupAction;
import FrierenMod.actions.PlayRandomCardAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SayThinkingSpell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(SayThinkingSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardType.SKILL, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.NONE);

    public SayThinkingSpell() {
        super(info);
    }

//    public SayThinkingSpell(CardColor color) {
//        super(ID, 2, CardType.SKILL, color, CardRarity.UNCOMMON, CardTarget.NONE);
//    }

    @Override
    public void initializeSpecifiedAttributes() {
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
        int amt = CombatHelper.getManaNumInHand();
        this.addToBot(new ExhaustManaInCardGroupAction(amt,1));
        for (int i = 0; i < amt * magicNumber; i++)
            addToBot(new PlayRandomCardAction(
                    (AbstractDungeon.getCurrRoom()).monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false));
    }
}
