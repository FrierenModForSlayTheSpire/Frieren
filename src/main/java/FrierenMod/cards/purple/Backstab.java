package FrierenMod.cards.purple;

import FrierenMod.actions.BackstabAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Backstab extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Backstab.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, AbstractCard.CardType.ATTACK, CardEnums.FERN_CARD, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ALL_ENEMY);

    public Backstab() {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.damage = this.baseDamage = 12;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new BackstabAction(p, this.baseDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE, upgraded));
    }
}
