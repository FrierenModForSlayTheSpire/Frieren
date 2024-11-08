package FrierenMod.cards.purple;

import FrierenMod.cards.AbstractBaseCard;
import FrierenMod.enums.CardEnums;
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
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class RaidRace extends AbstractBaseCard {
    public static final String ID = ModInformation.makeID(RaidRace.class.getSimpleName());
    public static final CardInfo info = new CardInfo(ID, 3, CardType.ATTACK, CardEnums.FERN_CARD, CardRarity.UNCOMMON, CardTarget.ENEMY);

    public RaidRace() {
        super(info);
    }

    @Override
    public void initializeSpecifiedAttributes() {
        this.damage = this.baseDamage = 21;
        this.tags.add(Enum.SPEED);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(6);
        }
    }

    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        int speedPlayed = (int) AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().filter(card -> card.hasTag(Enum.SPEED)).count();
        setCostForTurn(this.cost - speedPlayed);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        int speedPlayed = (int) AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().filter(card -> card.hasTag(Enum.SPEED)).count();
        setCostForTurn(this.cost - speedPlayed);
    }

    public void atTurnStart() {
        resetAttributes();
        applyPowers();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public AbstractCard makeCopy() {
        AbstractCard tmp = new RaidRace();
        if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null &&
                (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
            int speedPlayed = (int) AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().filter(card -> card.hasTag(Enum.SPEED)).count();
            setCostForTurn(this.cost - speedPlayed);
        }
        return tmp;
    }
}

