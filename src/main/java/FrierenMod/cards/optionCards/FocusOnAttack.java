package FrierenMod.cards.optionCards;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.powers.ConcentrationPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FocusOnAttack extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(FocusOnAttack.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -2, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    public AbstractMonster target;

    public FocusOnAttack() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.damage = this.baseDamage = 9;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    public void onChoseThisOption() {
        if (target != null)
            this.addToBot(new DamageAction(target, new DamageInfo(AbstractDungeon.player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ConcentrationPower(3)));
    }
}
