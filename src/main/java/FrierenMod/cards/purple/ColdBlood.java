package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ColdBlood extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(ColdBlood.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 2, CardType.ATTACK, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);

    public ColdBlood() {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.damage = this.baseDamage = 20;
        this.raidNumber = this.baseRaidNumber = 2;
        this.tags.add(Enum.RAID);
    }

    @Override
    public void triggerOnGlowCheck() {
        boolean effect1 = CombatHelper.canRaidTakeEffect(this.raidNumber);
        boolean effect2 = false;
        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty()) {
            AbstractCard judgeCard = null;
            if (!upgraded) {
                judgeCard = AbstractDungeon.actionManager.cardsPlayedThisCombat
                        .get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1);
            } else {
                for (int i = AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1; i > 0; i--) {
                    if (AbstractDungeon.actionManager.cardsPlayedThisCombat
                            .get(i).hasTag(Enum.RAID)) {
                        judgeCard = AbstractDungeon.actionManager.cardsPlayedThisCombat
                                .get(i);
                        break;
                    }
                }
            }
            effect2 = judgeCard instanceof AbstractBaseCard && ((AbstractBaseCard) judgeCard).isRaidTriggered;
        }
        if (effect1 && effect2) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            return;
        }
        if (effect1 || effect2) {
            this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
            return;
        }
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ActionHelper.addToBotAbstract(() -> {
            if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2) {
                boolean effect2;
                AbstractCard judgeCard = null;
                if (!upgraded) {
                    judgeCard = AbstractDungeon.actionManager.cardsPlayedThisCombat
                            .get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2);
                } else {
                    for (int i = AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2; i > 0; i--) {
                        if (AbstractDungeon.actionManager.cardsPlayedThisCombat
                                .get(i).hasTag(Enum.RAID)) {
                            judgeCard = AbstractDungeon.actionManager.cardsPlayedThisCombat
                                    .get(i);
                            break;
                        }
                    }
                }
                effect2 = judgeCard instanceof AbstractBaseCard && ((AbstractBaseCard) judgeCard).isRaidTriggered;
                if (effect2) {
                    this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                }
            }
        });
        this.isRaidTriggered = CombatHelper.triggerRaid(raidNumber, () -> this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL)));
    }
}

