package FrierenMod.cards.white;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FrierenMod.gameHelpers.ChantHelper.getAllManaNum;


public class Free extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(Free.class.getSimpleName());

    public Free() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.damage = this.baseDamage = 12;
    }

    @Override
    public void triggerOnGlowCheck() {
        if (getAllManaNum() % 6 == 0)
            this.glowColor = new Color(140, 87, 127, 1);
        else if (getAllManaNum() % 2 == 0)
            this.glowColor = GOLD_BORDER_GLOW_COLOR;
        else if (getAllManaNum() % 3 == 0)
            this.glowColor = GOLD_BORDER_GLOW_COLOR;
        else
            this.glowColor = BLUE_BORDER_GLOW_COLOR;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAllEnemiesAction(p, this.baseDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        if (getAllManaNum() % 2 == 0)
            this.addToBot(new DamageAllEnemiesAction(p, this.baseDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if (getAllManaNum() % 3 == 0)
            this.addToBot(new DamageAllEnemiesAction(p, this.baseDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(5);
        }
    }

    public AbstractCard makeCopy() {
        return new Free();
    }
}
