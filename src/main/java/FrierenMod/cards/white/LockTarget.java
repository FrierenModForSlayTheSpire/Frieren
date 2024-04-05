package FrierenMod.cards.white;

import FrierenMod.actions.LockTargetAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FrierenMod.gameHelpers.ChantHelper.getManaNumInDiscardPile;
import static FrierenMod.gameHelpers.ChantHelper.getManaNumInDrawPile;

public class LockTarget extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(LockTarget.class.getSimpleName());

    public LockTarget() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseMagicNumber = this.magicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = getManaNumInDrawPile() >= getManaNumInDiscardPile() ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new LockTargetAction(this.magicNumber, this.damage, this.damageTypeForTurn, m));
    }
}
