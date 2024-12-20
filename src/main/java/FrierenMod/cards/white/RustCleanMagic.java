package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.SlotBgHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.HexPower;

public class RustCleanMagic extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(RustCleanMagic.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.SKILL, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);

    public RustCleanMagic() {
        super(info);
    }

//    public RustCleanMagic(CardColor color) {
//        super(ID, 1, CardType.SKILL, color, CardRarity.UNCOMMON, CardTarget.ENEMY);
//    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.tags.add(AbstractBaseCard.Enum.LEGENDARY_SPELL);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (m.type != AbstractMonster.EnemyType.BOSS)
                    for (AbstractPower po : m.powers) {
                        if (po.type == AbstractPower.PowerType.BUFF) {
                            this.addToTop(new RemoveSpecificPowerAction(m, m, po.ID));
                            break;
                        }
                    }
                for (AbstractPower po : p.powers) {
                    if (po.type == AbstractPower.PowerType.DEBUFF) {
                        if (po.ID.equals(HexPower.POWER_ID))
                            SlotBgHelper.unlockANewSlot("4006");
                        this.addToTop(new RemoveSpecificPowerAction(p, p, po.ID));
                        break;
                    }
                }
                this.isDone = true;
            }
        });
    }
}
