package FrierenMod.cards.white;

import FrierenMod.actions.LittleFireAction;
import FrierenMod.actions.ModifyCostAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LittleFire extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(LittleFire.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.COMMON, CardTarget.ENEMY);

    public LittleFire() {
        super(info);
    }

//    public LittleFire(CardColor color) {
//        super(ID, 1, CardType.ATTACK, color, CardRarity.COMMON, CardTarget.ENEMY);
//    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.damage = this.baseDamage = 3;
        this.magicNumber = this.baseMagicNumber = 2;
        this.tags.add(AbstractBaseCard.Enum.COST_REST);
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
        if (m != null)
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        addToBot(new LittleFireAction(this, this.magicNumber));
        this.addToBot(new ModifyCostAction(this.uuid, 1));
    }

    @Override
    public void afterSynchroFinished(AbstractCard card) {
        this.addToBot(new DiscardToHandAction(this));
    }
}