package FrierenMod.cards.white;

import FrierenMod.actions.EleganceAction;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Elegance extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(Elegance.class.getSimpleName());
    public Elegance() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.misc = 8;
        this.secondMisc = 0;
        this.baseDamage = this.misc;
        this.baseBlock = this.misc;
        this.baseMagicNumber = this.secondMisc;
        this.exhaust = true;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new EleganceAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn),this));
    }
}
