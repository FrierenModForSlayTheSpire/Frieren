package FrierenMod.cards.white;

import FrierenMod.actions.OilSpellAction;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class OilSpell extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(OilSpell.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.COMMON, CardTarget.ALL_ENEMY);

    public OilSpell() {
        super(info);
    }


    @Override
    public void initializeSpecifiedAttributes() {
        this.damage = this.baseDamage = 12;
        this.magicNumber = this.baseMagicNumber = 8;
        this.tags.add(AbstractBaseCard.Enum.LEGENDARY_SPELL);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(4);
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        AbstractPlayer player = AbstractDungeon.player;
        this.isMagicNumberModified = false;
        float tmp = this.baseMagicNumber;
        for (AbstractRelic r : player.relics) {
            tmp = r.atDamageModify(tmp, this);
            if (this.baseMagicNumber != (int) tmp)
                this.isMagicNumberModified = true;
        }
        for (AbstractPower p : player.powers)
            tmp = p.atDamageGive(tmp, this.damageTypeForTurn, this);
        tmp = player.stance.atDamageGive(tmp, this.damageTypeForTurn, this);
        if (this.baseMagicNumber != (int) tmp)
            this.isMagicNumberModified = true;
        for (AbstractPower p : player.powers)
            tmp = p.atDamageFinalGive(tmp, this.damageTypeForTurn, this);
        if (tmp < 0.0F)
            tmp = 0.0F;
        if (this.baseMagicNumber != MathUtils.floor(tmp))
            this.isMagicNumberModified = true;
        this.magicNumber = MathUtils.floor(tmp);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        this.addToBot(new OilSpellAction(p, this.baseDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE, this.baseMagicNumber));
    }
}
