package FrierenMod.deprecated;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class Free extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Free.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);

    public Free() {
        super(info);
    }

//    public Free(CardColor color) {
//        super(ID, 2, CardType.ATTACK, color, CardRarity.UNCOMMON, CardTarget.ENEMY);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.damage = this.baseDamage = 12;
    }

    @Override
    public void triggerOnGlowCheck() {
        if (CombatHelper.getAllManaNum() % 6 == 0)
            this.glowColor = GREEN_BORDER_GLOW_COLOR;
        else if (CombatHelper.getAllManaNum() % 2 == 0)
            this.glowColor = GOLD_BORDER_GLOW_COLOR;
        else if (CombatHelper.getAllManaNum() % 3 == 0)
            this.glowColor = GOLD_BORDER_GLOW_COLOR;
        else
            this.glowColor = BLUE_BORDER_GLOW_COLOR;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAllEnemiesAction(p, this.baseDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        if (CombatHelper.getAllManaNum() % 2 == 0)
            this.addToBot(new DamageAllEnemiesAction(p, this.baseDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if (CombatHelper.getAllManaNum() % 3 == 0)
            this.addToBot(new DamageAllEnemiesAction(p, this.baseDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
        }
    }
}
