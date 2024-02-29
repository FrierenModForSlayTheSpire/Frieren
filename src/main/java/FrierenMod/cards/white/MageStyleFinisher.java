package FrierenMod.cards.white;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
public class MageStyleFinisher extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(MageStyleFinisher.class.getSimpleName());
    public MageStyleFinisher() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 5;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
            this.initializeDescription();
        }
    }

    public void applyPowers() {
        super.applyPowers();
        this.baseMagicNumber=0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c instanceof AbstractFrierenCard && ((AbstractFrierenCard) c).isMana)
                this.baseMagicNumber++;
        }
        initializeDescription();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < baseMagicNumber; i++) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }
}
