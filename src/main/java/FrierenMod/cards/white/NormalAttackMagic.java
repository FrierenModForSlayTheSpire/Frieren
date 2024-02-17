package FrierenMod.cards.white;

import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.gameHelpers.LegendMagicHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
public class NormalAttackMagic extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(NormalAttackMagic.class.getSimpleName());
    public NormalAttackMagic() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 0;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }
    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseMagicNumber = new LegendMagicHelper().getExhaustedMagicPowerNumber();
        this.baseDamage += this.baseMagicNumber;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;
        this.initializeDescription();
    }
    public void calculateCardDamage(AbstractMonster mo) {
        this.baseMagicNumber = new LegendMagicHelper().getExhaustedMagicPowerNumber();
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.baseMagicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;
        this.initializeDescription();
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.damage += this.magicNumber;
        this.calculateCardDamage(m);
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
        for (int i = 0; i < 2; i++) {
            this.addToBot(new DamageAction(
                    m,
                    new DamageInfo(
                            p,
                            this.damage,
                            this.damageTypeForTurn
                    )
            ));
        }
    }
}
