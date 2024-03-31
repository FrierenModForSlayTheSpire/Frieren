package FrierenMod.cards.white;

import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.effects.NormalAttackEffect;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.LegendarySpellHelper;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;

public class OrdinaryOffensiveMagic extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(OrdinaryOffensiveMagic.class.getSimpleName());

    public OrdinaryOffensiveMagic() {
        super(ID, 2, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public OrdinaryOffensiveMagic(CardColor color) {
        super(ID, 2, CardType.ATTACK, color, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    @Override
    public void initSpecifiedAttributes() {
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
        this.baseMagicNumber = LegendarySpellHelper.getExhaustedManaNumber();
        this.baseDamage += this.baseMagicNumber;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
        this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;
        this.initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        this.baseMagicNumber = LegendarySpellHelper.getExhaustedManaNumber();
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
        this.addToTop(new VFXAction(p, new NormalAttackEffect(), 0.1F, true));
        this.damage += this.magicNumber;
        this.calculateCardDamage(m);
        this.addToBot(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
        for (int i = 0; i < 2; i++) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
        }
    }
}
