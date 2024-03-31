package FrierenMod.cards.canAutoAdd.optionCards.zeerie.layer4;

import FrierenMod.cardMods.DexterityMod;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DexterityOption extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(DexterityOption.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE);
    public static final CardInfo info2 = new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
    private AbstractCard currentLegendMagic;
    private int dexterityAmt;
    public DexterityOption() {
        super(info);
    }
    public DexterityOption(AbstractCard currentLegendMagic, int dexterityAmt) {
        super(info2);
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