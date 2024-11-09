package FrierenMod.cards.tempCards;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Repent extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Repent.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    public Repent() {
        super(info);
        this.exhaust = true;
        this.selfRetain = true;
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
        if (AbstractDungeon.player.discardPile.size() != 0) {
            if(AbstractDungeon.player.discardPile.size() ==1) this.addToBot(new BetterDiscardPileToHandAction(1,0));
            this.addToBot(new BetterDiscardPileToHandAction(this.magicNumber,0));

        }    }
}