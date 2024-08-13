package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HotTeaSpell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(HotTeaSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.SKILL, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.SELF);

    public HotTeaSpell() {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.block = baseBlock = 12;
        this.tags.add(AbstractBaseCard.Enum.LEGENDARY_SPELL);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

    @Override
    protected void applyPowersToBlock() {
        this.isBlockModified = false;
        float tmp = this.baseBlock;
        tmp += CombatHelper.getAllManaNum();
        for (AbstractPower p : AbstractDungeon.player.powers)
            tmp = p.modifyBlock(tmp, this);
        for (AbstractPower p : AbstractDungeon.player.powers)
            tmp = p.modifyBlockLast(tmp);
        if (this.baseBlock != MathUtils.floor(tmp))
            this.isBlockModified = true;
        if (tmp < 0.0F)
            tmp = 0.0F;
        this.block = MathUtils.floor(tmp);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
    }
}
