package FrierenMod.powers;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.utils.ModInformation;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashPowerEffect;

import static FrierenMod.powers.AbstractBasePower.getImgTexture;

public class PaperPlaneSpellPower extends TwoAmountPower {
    public static final String POWER_ID = ModInformation.makeID(PaperPlaneSpellPower.class.getSimpleName());
    public static final String[] descriptions = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public static final int REST_AMT = 4;
    public int delta;

    public PaperPlaneSpellPower(int delta) {
        this.delta = delta;
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = REST_AMT;
        this.type = PowerType.BUFF;
        this.name = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
        this.region128 = getImgTexture(POWER_ID, 84);
        this.region48 = getImgTexture(POWER_ID, 32);
        this.updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        this.delta++;
        AbstractDungeon.effectList.add(new FlashPowerEffect(this));
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.amount2--;
            if (this.amount2 < 0)
                this.amount2 = 0;
        }
        if (card.hasTag(AbstractBaseCard.Enum.SYNCHRO)) {
            this.flash();
            this.amount--;
            if (this.amount <= 0) {
                this.amount = REST_AMT;
                this.amount2 += delta;
            }
        } else {
            this.amount = REST_AMT;
        }
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(descriptions[0], amount2, amount, delta);
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL && this.amount2 > 0)
            return damage * 2.0F;
        return damage;
    }

}