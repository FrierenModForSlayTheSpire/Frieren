package FrierenMod.monsters;

import FrierenMod.actions.ReplaceManaAction;
import FrierenMod.cards.tempCards.UpsideDown;
import FrierenMod.gameHelpers.ActionHelper;
import FrierenMod.powers.BanManaGainPower;
import FrierenMod.powers.CopyPower;
import FrierenMod.powers.GetPlayerBlockPower;
import FrierenMod.powers.WeakenedChantPower;
import FrierenMod.utils.ModInformation;
import FrierenMod.utils.MonsterRes;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BarricadePower;

import java.util.ArrayList;

import static java.util.Collections.shuffle;

public class Spiegel_Frieren extends AbstractMonster {
    public static final String MONSTER_ID = ModInformation.makeID(Spiegel_Frieren.class.getSimpleName());
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(MONSTER_ID);
    private boolean isFirstMove = true;
    private final ArrayList<Byte> debuffQueue = new ArrayList<>();
    private int finalStageAttackTimes = 2;
    private static final int BASE_DAMAGE = 20;
    private static final int BASE_DAMAGE_ASC = 30;
    private static final int ATTACK_TIMES_DELTA = 1;
    private static final int ATTACK_TIMES_DELTA_ASC = 2;
    private static final int WEAKENED_CHANT_POWER_AMT = 1;
    private static final int WEAKENED_CHANT_POWER_AMT_ASC = 2;

    public Spiegel_Frieren() {
        super(monsterStrings.NAME, MONSTER_ID, 500, 30.0F, -30.0F, 476.0F, 410.0F, MonsterRes.SPIEGEL_FRIEREN, -50.0F, 30.0F);
        this.type = EnemyType.BOSS;
        if (AbstractDungeon.ascensionLevel >= 19)
            this.damage.add(new DamageInfo(this, BASE_DAMAGE));
        else
            this.damage.add(new DamageInfo(this, BASE_DAMAGE_ASC));
        this.initDebuffQueue();
    }

    public void usePreBattleAction() {
        this.addToBot(new ApplyPowerAction(this, this, new CopyPower(this)));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new GetPlayerBlockPower(AbstractDungeon.player, this)));
        this.addToBot(new ApplyPowerAction(this, this, new BarricadePower(this)));
    }

    @Override
    public void takeTurn() {
        switch (this.nextMove) {
            case 1:
                this.addToBot(new DamageAction(AbstractDungeon.player, this.damage
                        .get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                ActionHelper.addToBotAbstract(() -> {
                    if (!this.debuffQueue.isEmpty()) {
                        getMove((int) this.debuffQueue.get(0));
                        this.debuffQueue.remove(0);
                    } else {
                        setMove(monsterStrings.MOVES[5], (byte) 6, Intent.ATTACK, this.damage.get(0).base, this.finalStageAttackTimes, true);
                    }
                });
                break;
            case 2:
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new BanManaGainPower(AbstractDungeon.player)));
                setMove(monsterStrings.MOVES[0], (byte) 1, AbstractMonster.Intent.ATTACK, this.damage.get(0).base);
                break;
            case 3:
                this.addToBot(new ReplaceManaAction(AbstractDungeon.player, 5));
                setMove(monsterStrings.MOVES[0], (byte) 1, AbstractMonster.Intent.ATTACK, this.damage.get(0).base);
                break;
            case 4:
                this.addToBot(new MakeTempCardInDrawPileAction(new UpsideDown(), 1, true, true));
                setMove(monsterStrings.MOVES[0], (byte) 1, AbstractMonster.Intent.ATTACK, this.damage.get(0).base);
                break;
            case 5:
                if (AbstractDungeon.ascensionLevel >= 19)
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new WeakenedChantPower(AbstractDungeon.player, WEAKENED_CHANT_POWER_AMT_ASC), 1));
                else
                    this.addToBot(new ApplyPowerAction(AbstractDungeon.player, this, new WeakenedChantPower(AbstractDungeon.player, WEAKENED_CHANT_POWER_AMT), 1));
                setMove(monsterStrings.MOVES[0], (byte) 1, AbstractMonster.Intent.ATTACK, this.damage.get(0).base);
                break;
            case 6:
                for (int i = 0; i < this.finalStageAttackTimes; i++) {
                    this.addToBot(new DamageAction(AbstractDungeon.player, this.damage
                            .get(0), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                }
                if (AbstractDungeon.ascensionLevel >= 19)
                    this.finalStageAttackTimes += ATTACK_TIMES_DELTA_ASC;
                else
                    this.finalStageAttackTimes += ATTACK_TIMES_DELTA;
                setMove(monsterStrings.MOVES[5], (byte) 6, Intent.ATTACK, this.damage.get(0).base, this.finalStageAttackTimes, true);
                break;
        }
    }

    public void initDebuffQueue() {
        this.debuffQueue.add((byte) 3);
        this.debuffQueue.add((byte) 4);
        this.debuffQueue.add((byte) 5);
        shuffle(this.debuffQueue, AbstractDungeon.aiRng.random);
    }

    @Override
    protected void getMove(int i) {
        if (this.isFirstMove) {
            setMove(monsterStrings.MOVES[1], (byte) 2, AbstractMonster.Intent.STRONG_DEBUFF);
            this.isFirstMove = false;
            return;
        }
        if(i >= 1)
            setMove(monsterStrings.MOVES[i - 1], (byte) i, Intent.STRONG_DEBUFF);
    }
}
