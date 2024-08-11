package FrierenMod.cards.magicItems.factors;

import FrierenMod.cards.magicItems.AbstractMagicItem;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;

public class Transcendence extends AbstractMagicItem {
    public static final String ID = ModInformation.makeID(Transcendence.class.getSimpleName());

    public Transcendence() {
        super(ID);
        loadRarity(MagicItemRarity.RARE);
        this.manaNeedAddCoefficient = 1;
    }

    @Override
    public void takeEffect() {
        if (!p.hasPower(MantraPower.POWER_ID))
            this.addToBot(new ApplyPowerAction(p, p, new MantraPower(p, 0)));
        this.addToBot(new ApplyPowerAction(p, p, new MantraPower(p, secondMagicNumber)));
    }
}
