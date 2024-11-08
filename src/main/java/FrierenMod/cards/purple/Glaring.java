package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class Glaring extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Glaring.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.ATTACK, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);

    public Glaring() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.damage = this.baseDamage = 12;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }

    @Override
    public void applyPowers() {
        applyPowersToBlock();
        AbstractPlayer player = AbstractDungeon.player;
        this.isDamageModified = false;
        if (!this.isMultiDamage) {
            float tmp = this.baseDamage;
            for (AbstractRelic r : player.relics) {
                tmp = r.atDamageModify(tmp, this);
                if (this.baseDamage != (int) tmp)
                    this.isDamageModified = true;
            }
            for (AbstractPower p : player.powers)
                tmp = p.atDamageGive(tmp, this.damageTypeForTurn, this);
            tmp = player.stance.atDamageGive(tmp, this.damageTypeForTurn, this);
            if (this.baseDamage != (int) tmp)
                this.isDamageModified = true;
            for (AbstractPower p : player.powers)
                tmp = p.atDamageFinalGive(tmp, this.damageTypeForTurn, this);
            tmp += CombatHelper.getConcentrationPowerAmt();
            if (tmp < 0.0F)
                tmp = 0.0F;
            if (this.baseDamage != MathUtils.floor(tmp))
                this.isDamageModified = true;
            this.damage = MathUtils.floor(tmp);
        } else {
            ArrayList<AbstractMonster> m = (AbstractDungeon.getCurrRoom()).monsters.monsters;
            float[] tmp = new float[m.size()];
            int i;
            for (i = 0; i < tmp.length; i++)
                tmp[i] = this.baseDamage;
            for (i = 0; i < tmp.length; i++) {
                for (AbstractRelic r : player.relics) {
                    tmp[i] = r.atDamageModify(tmp[i], this);
                    if (this.baseDamage != (int) tmp[i])
                        this.isDamageModified = true;
                }
                for (AbstractPower p : player.powers)
                    tmp[i] = p.atDamageGive(tmp[i], this.damageTypeForTurn, this);
                tmp[i] = player.stance.atDamageGive(tmp[i], this.damageTypeForTurn, this);
                if (this.baseDamage != (int) tmp[i])
                    this.isDamageModified = true;
            }
            for (i = 0; i < tmp.length; i++) {
                for (AbstractPower p : player.powers)
                    tmp[i] = p.atDamageFinalGive(tmp[i], this.damageTypeForTurn, this);
            }
            for (i = 0; i < tmp.length; i++) {
                if (tmp[i] < 0.0F)
                    tmp[i] = 0.0F;
            }
            this.multiDamage = new int[tmp.length];
            for (i = 0; i < tmp.length; i++) {
                if (this.baseDamage != (int) tmp[i])
                    this.isDamageModified = true;
                this.multiDamage[i] = MathUtils.floor(tmp[i]);
            }
            this.damage = this.multiDamage[0];
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }
}