package FrierenMod.cards.white.chant;

import FrierenMod.actions.ChantAction;
import FrierenMod.actions.MakeMagicPowerInDiscardAction;
import FrierenMod.actions.MakeMagicPowerInDrawPileAction;
import FrierenMod.actions.MakeMagicPowerInHandAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TrueFace extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(TrueFace.class.getSimpleName());
    public TrueFace() {
        super(ID, 1, CardRarity.RARE);
        this.chantX = this.baseChantX = 6;
        this.isChantCard = true;
        this.exhaust = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChantAction(this.chantX));
        this.addToBot(new GainEnergyAction(3));
        this.addToBot(new DrawCardAction(3));
        this.addToBot(new MakeMagicPowerInDrawPileAction(3));
        this.addToBot(new MakeMagicPowerInHandAction(3));
        this.addToBot(new MakeMagicPowerInDiscardAction(3));
    }
}
