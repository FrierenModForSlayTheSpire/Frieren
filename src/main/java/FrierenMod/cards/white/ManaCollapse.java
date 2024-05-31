package FrierenMod.cards.white;

import FrierenMod.actions.MakeManaInHandAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ManaCollapse extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ManaCollapse.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);

    public ManaCollapse() {
        super(info);
    }

//    public ManaCollapse(CardColor color) {
//        super(ID, 1, CardType.ATTACK, color, CardRarity.UNCOMMON, CardTarget.ENEMY);
//    }

    @Override
    public void initSpecifiedAttributes() {
        this.damage = this.baseDamage = 9;
        this.magicNumber = this.baseMagicNumber = 2;
        this.shuffleBackIntoDrawPile = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1 < 10) {
            this.addToBot(new MakeManaInHandAction(this.magicNumber));
        }
    }

    public void applyPowers() {
        super.applyPowers();
        this.shuffleBackIntoDrawPile = AbstractDungeon.actionManager.cardsPlayedThisTurn.size() < 10;
    }

    public void triggerOnGlowCheck() {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() < 10) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
