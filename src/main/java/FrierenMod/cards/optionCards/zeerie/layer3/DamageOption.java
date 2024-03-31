package FrierenMod.cards.optionCards.zeerie.layer3;

import FrierenMod.cardMods.DamageMod;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DamageOption extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(DamageOption.class.getSimpleName());
    private AbstractCard currentLegendMagic;
    private int damageAmt;
    public DamageOption() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.ATTACK, CardTarget.ENEMY);
    }
    public DamageOption(AbstractCard currentLegendMagic, int damageAmt) {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.ATTACK, CardTarget.ENEMY);
        this.currentLegendMagic = currentLegendMagic;
        this.damageAmt = damageAmt;
        this.damage = this.baseDamage = damageAmt;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    public void onChoseThisOption() {
        CardModifierManager.addModifier(this.currentLegendMagic, new DamageMod(this.damageAmt));
    }
}