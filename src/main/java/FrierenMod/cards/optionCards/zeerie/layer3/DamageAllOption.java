package FrierenMod.cards.optionCards.zeerie.layer3;

import FrierenMod.cardMods.DamageAllMod;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DamageAllOption extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(DamageAllOption.class.getSimpleName());
    private AbstractCard currentLegendMagic;
    private int damageAmt;
    public DamageAllOption() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.ATTACK, CardTarget.ALL_ENEMY);
    }
    public DamageAllOption(AbstractCard currentLegendMagic, int damageAmt) {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.ATTACK, CardTarget.ALL_ENEMY);
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
        CardModifierManager.addModifier(this.currentLegendMagic, (AbstractCardModifier)new DamageAllMod(this.damageAmt));
    }
}