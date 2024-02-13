package FrierenMod.cards.white;

import FrierenMod.actions.MakeMagicPowerInHandAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.powers.SpeedFlowPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AccelerateFlow extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(AccelerateFlow.class.getSimpleName());
    public AccelerateFlow() {
        super(ID,2, CardRarity.UNCOMMON);
        this.magicNumber = this.baseMagicNumber = 2;
        this.isMagicSource = true;
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
        this.addToBot(new MakeMagicPowerInHandAction(this.magicNumber,canGainMagic));
        this.addToBot(new ApplyPowerAction(p,p,new SpeedFlowPower(p)));
    }
}
