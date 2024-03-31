package FrierenMod.cards.white;

import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.effects.KissEffect;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Kiss extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(Kiss.class.getSimpleName());
    public Kiss() {
        super(ID, 3, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.isEthereal=true;
        this.exhaust = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(p,new KissEffect(),0.1F));
        this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, 99, false), 99));
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, 99, false), 99));
    }
}


