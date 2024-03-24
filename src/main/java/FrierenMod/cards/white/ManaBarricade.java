package FrierenMod.cards.white;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.powers.ManaBarricadePower;
import FrierenMod.powers.RingletFormPower;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ManaBarricade extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(ManaBarricade.class.getSimpleName());
    public ManaBarricade() {
        super(ID, 2, CardType.POWER, CardRarity.RARE);
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        boolean powerExists = false;
        for (AbstractPower pow : p.powers) {
            if (pow.ID.equals("ManaBarricadePower")) {
                powerExists = true;
                break;
            }
        }
        if (!powerExists)
            this.addToBot(new ApplyPowerAction(p, p, new ManaBarricadePower(p)));
    }

}
