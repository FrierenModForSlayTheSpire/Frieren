package FrierenMod.cards.canAutoAdd.optionCards.zeerie.layer2;

import FrierenMod.cardMods.SelfRetainMod;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SelfRetainOption extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(SelfRetainOption.class.getSimpleName());
    private AbstractCard currentLegendMagic;
    private int selfRetainAmt;

    public SelfRetainOption() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.NONE);
    }

    public SelfRetainOption(AbstractCard currentLegendMagic, int selfRetainAmt) {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
        this.currentLegendMagic = currentLegendMagic;
        this.selfRetainAmt = selfRetainAmt;
        this.magicNumber = this.baseMagicNumber = selfRetainAmt;
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
        CardModifierManager.addModifier(this.currentLegendMagic, new SelfRetainMod(this.selfRetainAmt));
    }
}