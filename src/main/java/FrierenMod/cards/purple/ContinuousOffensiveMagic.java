package FrierenMod.cards.purple;

import FrierenMod.cardMods.DamageMod;
import FrierenMod.cardMods.ExhaustEtherealMod;
import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.cards.tempCards.SpecializedOffensiveMagic;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.powers.AbstractBasePower;
import FrierenMod.powers.FusionPower.AbstractFusionPower;
import FrierenMod.powers.FusionPower.DamageFusionPower;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ContinuousOffensiveMagic extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ContinuousOffensiveMagic.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 1, CardType.SKILL, CardEnums.FERN_CARD, CardRarity.RARE, CardTarget.SELF);

    public ContinuousOffensiveMagic() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.raidNumber = this.baseRaidNumber = 2;
        this.magicNumber = this.baseMagicNumber = 10;
        this.tags.add(Enum.RAID);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(5);
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.cardsToPreview = CombatHelper.getPreviewSpecializedOffensiveMagic(this.magicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        SpecializedOffensiveMagic magic = new SpecializedOffensiveMagic();
        ActionHelper.addToBotAbstract(() -> {
            int baseDamage = this.magicNumber;
            magic.baseDamage = baseDamage;
            CardModifierManager.addModifier(magic, new DamageMod(baseDamage));
            for (AbstractPower po : AbstractDungeon.player.powers) {
                if (po instanceof AbstractFusionPower) {
                    if (po instanceof DamageFusionPower)
                        magic.damage = magic.baseDamage += po.amount;
                    else
                        CardModifierManager.addModifier(magic, ((AbstractFusionPower) po).modifier);
                    this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, po));
                }
            }
            CardModifierManager.addModifier(magic, new ExhaustEtherealMod());
            for (AbstractPower po : AbstractDungeon.player.powers) {
                if (po instanceof AbstractBasePower) {
                    ((AbstractBasePower) po).beforeGainSpecializedOffensiveMagic(magic);
                }
            }
            magic.modifyCostForCombat(-9);
            this.addToBot(new MakeTempCardInHandAction(magic));
            for (AbstractPower po : AbstractDungeon.player.powers) {
                if (po instanceof AbstractBasePower) {
                    ((AbstractBasePower) po).afterGainSpecializedOffensiveMagic(magic);
                }
            }
        });
        this.isRaidTriggered = CombatHelper.triggerRaid(raidNumber, () -> {
            this.addToBot(new MakeTempCardInHandAction(magic));
        });
    }
}

