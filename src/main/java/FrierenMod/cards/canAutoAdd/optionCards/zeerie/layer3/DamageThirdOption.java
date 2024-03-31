package FrierenMod.cards.canAutoAdd.optionCards.zeerie.layer3;

import FrierenMod.cardMods.DamageThirdMod;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DamageThirdOption extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(DamageThirdOption.class.getSimpleName());
    private AbstractCard currentLegendMagic;
    private int damageAmt;
    public DamageThirdOption() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.ATTACK, CardTarget.ENEMY);
    }
    public DamageThirdOption(AbstractCard currentLegendMagic, int damageAmt) {
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
        CardModifierManager.addModifier(this.currentLegendMagic, new DamageThirdMod(this.damageAmt));
    }
}