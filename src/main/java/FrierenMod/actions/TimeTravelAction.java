package FrierenMod.actions;

import FrierenMod.utils.ObjectCloner;
import FrierenMod.gameHelpers.State;
import FrierenMod.powers.TimeTravelPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.ArrayList;

public class TimeTravelAction extends AbstractGameAction {
    private final State state;
    private final AbstractCard excludeCard;
    private final ArrayList<AbstractPotion> potions = new ArrayList<>();
    private final ArrayList<AbstractRelic> relics = new ArrayList<>();
    private final ArrayList<AbstractPower> powers = new ArrayList<>();
    private final ArrayList<AbstractCard> drawPile = new ArrayList<>();
    private final ArrayList<AbstractCard> hand = new ArrayList<>();
    private final ArrayList<AbstractCard> discardPile = new ArrayList<>();
    private final ArrayList<AbstractCard> exhaustPile = new ArrayList<>();
    private final ArrayList<AbstractCard> cardsPlayedThisTurn = new ArrayList<>();
    private final boolean upgraded;

    public TimeTravelAction(AbstractCard excludeCard, boolean upgraded) {
        this.excludeCard = excludeCard;
        this.upgraded = upgraded;
        this.init();
        int hp = AbstractDungeon.player.currentHealth;
        int maxHp = AbstractDungeon.player.maxHealth;
        int gold = AbstractDungeon.player.gold;
        int block = AbstractDungeon.player.currentBlock;
        int energy = EnergyPanel.totalCount;
        int maxEnergy = AbstractDungeon.player.maxOrbs;
        state = new State(hp, maxHp, gold, potions, relics, block, powers, energy, maxEnergy, drawPile, hand, discardPile, exhaustPile, cardsPlayedThisTurn);
    }

    @Override
    public void update() {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new TimeTravelPower(AbstractDungeon.player, state, upgraded)));
        this.isDone = true;
    }

    private void init() {
        potions.addAll(AbstractDungeon.player.potions);
        for (AbstractRelic relic : AbstractDungeon.player.relics) {
            AbstractRelic tmpRelic = relic.makeCopy();
            tmpRelic.counter = relic.counter;
            tmpRelic.usedUp = relic.usedUp;
            relics.add(tmpRelic);
        }
        for (AbstractPower power : AbstractDungeon.player.powers) {
            powers.add(ObjectCloner.copyObject(power));
        }
        drawPile.addAll(AbstractDungeon.player.drawPile.group);
        hand.addAll(AbstractDungeon.player.hand.group);
        hand.remove(excludeCard);
        discardPile.addAll(AbstractDungeon.player.discardPile.group);
        exhaustPile.addAll(AbstractDungeon.player.exhaustPile.group);
        cardsPlayedThisTurn.addAll(AbstractDungeon.actionManager.cardsPlayedThisTurn);
    }
}
