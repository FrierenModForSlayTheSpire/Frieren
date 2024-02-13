package FrierenMod.cards.white;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
public class BabySleeping extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(BabySleeping.class.getSimpleName());
    public BabySleeping(){
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.block = baseBlock = 7;
        this.magicNumber = this.baseMagicNumber = 1;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p,p,this.block));
        this.addToBot(new RetainCardsAction(p, this.magicNumber));
    }
}


