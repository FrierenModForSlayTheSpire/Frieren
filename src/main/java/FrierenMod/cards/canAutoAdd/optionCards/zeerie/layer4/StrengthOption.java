package FrierenMod.cards.canAutoAdd.optionCards.zeerie.layer4;

import FrierenMod.cardMods.StrengthMod;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class StrengthOption extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(StrengthOption.class.getSimpleName());
    private AbstractCard currentLegendMagic;
    public StrengthOption() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
    }
    public StrengthOption(AbstractCard currentLegendMagic) {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
        this.currentLegendMagic = currentLegendMagic;
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
        CardModifierManager.addModifier(this.currentLegendMagic, new StrengthMod());
    }
}