package FrierenMod.cards.white;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
import FrierenMod.gameHelpers.CombatHelper;
import FrierenMod.utils.CardInfo;
import FrierenMod.utils.ModInformation;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

public class Famehameha extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(Famehameha.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 5, CardType.ATTACK, CardEnums.FRIEREN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);

    public Famehameha() {
        super(info);
    }

    @Override
    public void initSpecifiedAttributes() {
        this.damage = this.baseDamage = 31;
        this.tags.add(AbstractBaseCard.Enum.COST_REST);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        int tmp = Math.max(this.cost - CombatHelper.getContinualSynchroMaxTimes(), 0);
        if (costForTurn > tmp)
            this.setCostForTurn(tmp);
    }

    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        int tmp = Math.max(this.cost - CombatHelper.getContinualSynchroMaxTimes(), 0);
        if (costForTurn > tmp)
            this.setCostForTurn(tmp);
    }

    public void atTurnStart() {
        this.resetAttributes();
        this.applyPowers();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(10);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    public AbstractCard makeCopy() {
        AbstractCard card = new Famehameha();
        if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            int tmp = Math.max(this.cost - CombatHelper.getContinualSynchroMaxTimes(), 0);
            if (costForTurn > tmp)
                this.setCostForTurn(tmp);
        }
        return card;
    }
}
