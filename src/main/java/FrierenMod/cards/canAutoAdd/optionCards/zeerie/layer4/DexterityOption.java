package FrierenMod.cards.canAutoAdd.optionCards.zeerie.layer4;

import FrierenMod.cardMods.DexterityMod;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DexterityOption extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(DexterityOption.class.getSimpleName());
    private AbstractCard currentLegendMagic;
    private int dexterityAmt;
    public DexterityOption() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.POWER, CardTarget.NONE);
    }
    public DexterityOption(AbstractCard currentLegendMagic, int dexterityAmt) {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.POWER, CardTarget.NONE);
        this.currentLegendMagic = currentLegendMagic;
        this.dexterityAmt = dexterityAmt;
        this.magicNumber = this.baseMagicNumber = dexterityAmt;
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
        CardModifierManager.addModifier(this.currentLegendMagic, new DexterityMod(this.dexterityAmt));
    }
}