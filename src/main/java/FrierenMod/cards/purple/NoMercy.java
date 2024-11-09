package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.SpecializedOffensiveMagic;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class NoMercy extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(NoMercy.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final CardInfo info = new CardInfo(ID, 1, CardType.ATTACK, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);

    public NoMercy() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.damage = this.baseDamage = 8;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = 1 + (int) AbstractDungeon.player.exhaustPile.group.stream().filter(card -> card.cardID.equals(SpecializedOffensiveMagic.ID)).count();
        for (int i = 0; i < amount; i++) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int amount = 1 + (int) AbstractDungeon.player.exhaustPile.group.stream().filter(card -> card.cardID.equals(SpecializedOffensiveMagic.ID)).count();
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION;
        if (amount > 0)
            this.rawDescription += String.format(cardStrings.EXTENDED_DESCRIPTION[0], amount);
        initializeDescription();
    }

    public void applyPowers() {
        int amount = 1 + (int) AbstractDungeon.player.exhaustPile.group.stream().filter(card -> card.cardID.equals(SpecializedOffensiveMagic.ID)).count();
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        if (amount > 0)
            this.rawDescription += String.format(cardStrings.EXTENDED_DESCRIPTION[0], amount);
        initializeDescription();
    }
}