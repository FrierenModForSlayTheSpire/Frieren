package FrierenMod.cards.whitePurple;

import FrierenMod.cards.DualCard;
import FrierenMod.effects.NormalAttackEffect;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

public class OrdinaryOffensiveMagic extends DualCard {
    public static final String ID = ModInformation.makeID(OrdinaryOffensiveMagic.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static CardInfo info = new CardInfo(ID, 2, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);
    public static CardInfo info2 = new CardInfo(ID, 2, CardType.ATTACK, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY, true);

    public OrdinaryOffensiveMagic() {
        super(info);
    }

    public OrdinaryOffensiveMagic(CardInfo info) {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.isFrierenFernCard = true;
        this.baseDamage = 0;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    public void applyPowers() {
        int manaCount = CombatHelper.getManaNumInExhaustPile();
        if (manaCount > 0) {
            this.baseDamage = manaCount * 2;
            super.applyPowers();
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }
    }
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int manaCount = CombatHelper.getManaNumInExhaustPile();
        this.baseDamage = manaCount * 2;
        this.calculateCardDamage(m);
        this.addToTop(new VFXAction(p, new NormalAttackEffect(), 0.1F, true));
        this.addToBot(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }
}
