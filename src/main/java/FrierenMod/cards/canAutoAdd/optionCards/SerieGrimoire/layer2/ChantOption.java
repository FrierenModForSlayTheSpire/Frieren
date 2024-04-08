package FrierenMod.cards.canAutoAdd.optionCards.SerieGrimoire.layer2;

import FrierenMod.cardMods.ChantMod;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChantOption extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ChantOption.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE);
    public static final CardInfo info2 = new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
    private AbstractCard currentLegendMagic;
    private int chantAmt;
    public ChantOption() {
        super(info);
    }
    public ChantOption(AbstractCard currentLegendMagic, int chantAmt) {
        super(info2);
        this.currentLegendMagic = currentLegendMagic;
        this.chantAmt = chantAmt;
        this.chantX = this.baseChantX = this.chantAmt;
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
        CardModifierManager.addModifier(this.currentLegendMagic, (AbstractCardModifier)new ChantMod(this.chantAmt));
    }
}