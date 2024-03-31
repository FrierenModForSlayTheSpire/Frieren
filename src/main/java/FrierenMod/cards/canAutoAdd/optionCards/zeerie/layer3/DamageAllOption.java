package FrierenMod.cards.canAutoAdd.optionCards.zeerie.layer3;

import FrierenMod.cardMods.DamageAllMod;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DamageAllOption extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(DamageAllOption.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.ATTACK, CardTarget.NONE);
    public static final CardInfo info2 = new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.ATTACK, CardTarget.NONE);
    private AbstractCard currentLegendMagic;
    private int damageAmt;
    public DamageAllOption() {
        super(info);
    }
    public DamageAllOption(AbstractCard currentLegendMagic, int damageAmt) {
        super(info2);
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