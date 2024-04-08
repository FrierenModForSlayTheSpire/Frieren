package FrierenMod.cards.white;

import FrierenMod.actions.FlowAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.cards.tempCards.Mana;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Flow extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(Flow.class.getSimpleName());
    public Flow() {
        super(ID, 0, CardType.ATTACK,CardRarity.COMMON,CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 3;
        this.damage = this.baseDamage = 3;
        this.cardsToPreview = new Mana();
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
            this.upgradeDamage(2);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.addToBot(new FlowAction(this.magicNumber));
    }
}
