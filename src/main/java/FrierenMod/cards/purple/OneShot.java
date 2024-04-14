package FrierenMod.cards.purple;

import FrierenMod.actions.ChantAction;
import FrierenMod.actions.ModifyPowerStackAmtAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OneShot extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(OneShot.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 0, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.RARE, CardTarget.NONE);

    public OneShot() {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.isChantCard = true;
        this.chantX = this.baseChantX = 40;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeChantX(-5);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChantAction(this.chantX));
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters)
            if (mo.type != AbstractMonster.EnemyType.BOSS)
                this.addToBot(new InstantKillAction(mo));
        this.addToBot(new ModifyPowerStackAmtAction(CombatHelper.getConcentrationPower(), 0, false));
    }
}

