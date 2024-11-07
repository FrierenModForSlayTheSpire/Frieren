package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.powers.FusionPower.AbstractFusionPower;
import FrierenMod.powers.FusionPower.StrengthFusionPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class Catwalk extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Catwalk.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.RARE, CardTarget.SELF);

    public Catwalk() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ActionHelper.addToBotAbstract(() -> {
            for (AbstractPower po : p.powers) {
                if (po instanceof AbstractFusionPower) {
                    this.addToBot(new ApplyPowerAction(p, p, po, this.magicNumber));
                }
            }
        });
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1)));
        this.addToBot(new ApplyPowerAction(p, p, new StrengthFusionPower(1)));
    }
}

