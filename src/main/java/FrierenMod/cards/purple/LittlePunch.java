package FrierenMod.cards.purple;

import FrierenMod.actions.LittlePunchAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LittlePunch extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(LittlePunch.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, -1, CardType.ATTACK, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);

    public LittlePunch() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.baseDamage = 1;
        this.block = this.baseBlock = 3;
        this.isMultiDamage = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LittlePunchAction(p, this.multiDamage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse, this.block));
    }
}

