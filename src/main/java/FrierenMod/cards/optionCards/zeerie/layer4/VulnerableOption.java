package FrierenMod.cards.optionCards.zeerie.layer4;

import FrierenMod.cardMods.VulnerableMod;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class VulnerableOption extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(VulnerableOption.class.getSimpleName());
    private AbstractCard currentLegendMagic;
    private int stackAmt;
    public VulnerableOption() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION[0], CardType.SKILL, CardTarget.ALL_ENEMY);
    }
    public VulnerableOption(AbstractCard currentLegendMagic, int stackAmt) {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.ALL_ENEMY);
        this.currentLegendMagic = currentLegendMagic;
        this.stackAmt = stackAmt;
        this.magicNumber = this.baseMagicNumber = stackAmt;
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
        CardModifierManager.addModifier(this.currentLegendMagic, new VulnerableMod(this.stackAmt));
    }
}