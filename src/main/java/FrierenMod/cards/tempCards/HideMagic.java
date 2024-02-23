package FrierenMod.cards.tempCards;

import FrierenMod.actions.HideMagicAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HideMagic extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(HideMagic.class.getSimpleName());
    public HideMagic() {
        super(ID, 0, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.cardsToPreview = new MagicPower();
        this.exhaust = true;
        this.selfRetain = true;
    }
    @Override
    public boolean canUpgrade() {
        return false;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
       this.addToBot(new HideMagicAction());
    }
}
