package FrierenMod.cards.tempCards;

import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import FrierenMod.cards.AbstractBaseCard;


public class EmergencyFood extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(EmergencyFood.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);

    public EmergencyFood() {
        super(info);
        this.magicNumber = this.baseMagicNumber = 5;
        this.exhaust = true;
        this.selfRetain=true;
        this.setDisplayRarity(CardRarity.UNCOMMON);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new HealAction(p,p,this.magicNumber));
    }

}
