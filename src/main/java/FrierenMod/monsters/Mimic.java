package FrierenMod.monsters;

import FrierenMod.powers.MimicPower;
import FrierenMod.powers.SwallowedPower;
import FrierenMod.utils.ModInformation;
import FrierenMod.utils.MonsterRes;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Mimic extends AbstractMonster {
    public static final String MONSTER_ID = ModInformation.makeID(Mimic.class.getSimpleName());
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(MONSTER_ID);
    private boolean isFirstMove = true;
    private boolean haveNotFull = true;
    private static final int MAX_HEALTH = 160;
    private static final int MAX_HEALTH_ASC = 170;
    private static final int MAX_HEALTH_DELTA = 4;
    private static final int BASE_DAMAGE = 22;
    private static final int BASE_DAMAGE_ASC = 25;

    public Mimic() {
        super(monsterStrings.NAME, MONSTER_ID, MAX_HEALTH, 30.0F, -30.0F, 476.0F, 410.0F, MonsterRes.MIMIC, -50.0F, 30.0F);
        this.type = EnemyType.ELITE;
        if (AbstractDungeon.ascensionLevel >= 8) {
            setHp(MAX_HEALTH_ASC, MAX_HEALTH_ASC + MAX_HEALTH_DELTA);
        } else {
            setHp(MAX_HEALTH, MAX_HEALTH + MAX_HEALTH_DELTA);
        }
        if (AbstractDungeon.ascensionLevel >= 18)
            this.damage.add(new DamageInfo(this, BASE_DAMAGE_ASC));
        else
            this.damage.add(new DamageInfo(this, BASE_DAMAGE));
    }

    @Override
    public void usePreBattleAction() {
        this.addToBot(new ApplyPowerAction(this, this, new MimicPower(this, 5), 5));
    }


    @Override
    public void takeTurn() {
        switch (this.nextMove) {
            //伪装
            case 1:
                if (this.hasPower(MimicPower.POWER_ID))
                    setMove(monsterStrings.MOVES[0], (byte) 1, Intent.UNKNOWN);
                else
                    setMove(monsterStrings.MOVES[2], (byte) 2, Intent.STRONG_DEBUFF);
                break;
            //吞噬
            case 2:
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new SwallowedPower(AbstractDungeon.player, 2), 2));
                if (isFull()) {
                    setMove(monsterStrings.MOVES[3], (byte) 4, Intent.UNKNOWN);
                    haveNotFull = false;
                } else {
                    this.addToBot(new RollMoveAction(this));
                }
                break;
            //重击
            case 3:
                this.addToBot(new DamageAction(AbstractDungeon.player, this.damage
                        .get(0), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
                if (isFull()) {
                    setMove(monsterStrings.MOVES[3], (byte) 4, Intent.UNKNOWN);
                    haveNotFull = false;
                } else {
                    this.addToBot(new RollMoveAction(this));
                }
                break;
            //吃撑了
            case 4:
                this.addToBot(new TalkAction(this, monsterStrings.DIALOG[0], 1.0F, 2.0F));
                this.addToBot(new RollMoveAction(this));
                break;
        }
    }

    @Override
    protected void getMove(int i) {
        if (this.isFirstMove) {
            setMove(monsterStrings.MOVES[0], (byte) 1, Intent.UNKNOWN);
            this.isFirstMove = false;
            return;
        }
        if (i < 70) {
            if (!lastTwoMoves((byte) 3))
                setMove(monsterStrings.MOVES[1], (byte) 3, Intent.ATTACK, this.damage.get(0).base);
            else
                setMove(monsterStrings.MOVES[2], (byte) 2, Intent.STRONG_DEBUFF);
        } else {
            if (!lastMove((byte) 2))
                setMove(monsterStrings.MOVES[2], (byte) 2, Intent.STRONG_DEBUFF);
            else
                setMove(monsterStrings.MOVES[1], (byte) 3, Intent.ATTACK, this.damage.get(0).base);
        }
    }

    private boolean isFull() {
        AbstractPower po = AbstractDungeon.player.getPower(SwallowedPower.POWER_ID);
        return po != null && po.amount >= 6 && haveNotFull;
    }

    public static void register() {
        BaseMod.addMonster(Mimic.MONSTER_ID, Mimic::new);
    }
}
