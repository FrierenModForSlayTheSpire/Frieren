package FrierenMod.cards.optionCards.zeerie.layer4;

import FrierenMod.cardMods.UpgradeMod;
import FrierenMod.cards.AbstractFrierenCard;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class UpgradeOption extends AbstractFrierenCard {
    public static final String ID = ModInformation.makeID(UpgradeOption.class.getSimpleName());
    private AbstractCard currentLegendMagic;
    public UpgradeOption() {
        super(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
    }
    public UpgradeOption(AbstractCard currentLegendMagic) {
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
        CardModifierManager.addModifier(this.currentLegendMagic, new UpgradeMod());
    }
}