package FrierenMod.cards.white.chant;

import FrierenMod.actions.ChantAction;
import FrierenMod.actions.MakeManaInDiscardAction;
import FrierenMod.actions.MakeManaInDrawPileAction;
import FrierenMod.actions.MakeManaInHandAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TrueColours extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(TrueColours.class.getSimpleName());
    public TrueColours() {
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
        this.addToBot(new ChantAction(this.chantX,new DrawCardAction(3),new MakeManaInHandAction(3)));
        this.addToBot(new GainEnergyAction(3));
        this.addToBot(new MakeManaInDrawPileAction(3));
        this.addToBot(new MakeManaInDiscardAction(3));
    }
}
