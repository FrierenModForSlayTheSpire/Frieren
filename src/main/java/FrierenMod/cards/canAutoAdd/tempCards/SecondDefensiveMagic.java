package FrierenMod.cards.canAutoAdd.tempCards;

import FrierenMod.actions.ChantAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SecondDefensiveMagic extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(SecondDefensiveMagic.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, ModInformation.makeCardImgPath("DefendMagic"), 0, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    public SecondDefensiveMagic() {
        super(info);
        this.block = this.baseBlock = 3;
        this.isChantCard = true;
        this.chantX = this.baseChantX = 1;
        this.exhaust = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(2);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChantAction(this.chantX));
        this.addToBot(new GainBlockAction(p,this.block));
    }
}
