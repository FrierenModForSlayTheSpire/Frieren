package FrierenMod.cards.canAutoAdd.optionCards.zeerie.layer4;

import FrierenMod.cardMods.UpgradeMod;
import FrierenMod.cards.AbstractMagicianCard;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class UpgradeOption extends AbstractMagicianCard {
    public static final String ID = ModInformation.makeID(UpgradeOption.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION, CardType.SKILL, CardTarget.NONE);
    private AbstractCard currentLegendMagic;
    public UpgradeOption() {
        super(info);
    }
    public UpgradeOption(AbstractCard currentLegendMagic) {
        super(info);
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