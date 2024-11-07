package FrierenMod.cards.tempCards;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GetWell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(GetWell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);

    public GetWell() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.exhaust = true;
        this.selfRetain = true;
    }

    @Override
    public void upgrade() {
        if(!this.upgraded){
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ActionHelper.addToBotAbstract(() -> {
            for (AbstractPower po : p.powers) {
                if (!(po instanceof InvisiblePower))
                    if (po.type == AbstractPower.PowerType.DEBUFF) {
                        this.addToBot(new RemoveSpecificPowerAction(p, p, po));
                    }
            }
        });
        this.addToBot(new HealAction(p, p, 7));
    }
}

