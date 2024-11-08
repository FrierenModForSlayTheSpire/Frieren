package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.powers.ConcentrationPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class Meditation extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Meditation.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -1, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.SELF);

    public Meditation() {
        super(info);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
            if (!this.hasTag(Enum.SPEED))
                this.tags.add(Enum.SPEED);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ActionHelper.addToBotAbstract(() -> {
            int effect = EnergyPanel.getCurrentEnergy();
            if (this.energyOnUse != -1)
                effect = this.energyOnUse;
            if (p.hasRelic("Chemical X")) {
                effect += 2;
                p.getRelic("Chemical X").flash();
            }
            EnergyPanel.useEnergy(this.energyOnUse);
            if (effect > 0) {
                for (int i = 0; i < effect; i++) {
                    this.addToBot(new ApplyPowerAction(p, p, new ConcentrationPower(2)));
                    this.addToBot(new GainEnergyAction(1));
                }
            }
        });
    }
}

