package FrierenMod.cards.white;

import FrierenMod.actions.MakeManaInHandAction;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.powers.AccelerateFlowPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ClearMind extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(ClearMind.class.getSimpleName());
    public ClearMind() {
        super(ID,2, CardRarity.UNCOMMON);
        this.magicNumber = this.baseMagicNumber = 2;
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
        this.addToBot(new MakeManaInHandAction(this.magicNumber));
        this.addToBot(new ApplyPowerAction(p,p,new AccelerateFlowPower(p)));
    }
}
