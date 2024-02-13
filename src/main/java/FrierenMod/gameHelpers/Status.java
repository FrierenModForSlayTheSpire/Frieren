package FrierenMod.gameHelpers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class Status {
    public final int hp;
    public final int maxHp;
    public final int gold;
    public final ArrayList<AbstractPotion> potions;
    public final ArrayList<AbstractRelic> relics;
    public final int block;
    public final ArrayList<AbstractPower> powers;
    public final ArrayList<Integer> powerAmt;
    public final int energy;
    public final int maxEnergy;
    public final ArrayList<AbstractCard> drawPile;
    public final ArrayList<AbstractCard> hand;
    public final ArrayList<AbstractCard> discardPile;
    public final ArrayList<AbstractCard> exhaustPile;
    public Status(int hp, int maxHp, int gold, ArrayList<AbstractPotion> potions, ArrayList<AbstractRelic> relics, int block, ArrayList<AbstractPower> powers, ArrayList<Integer> powerAmt, int energy, int maxEnergy, ArrayList<AbstractCard> drawPile, ArrayList<AbstractCard> hand, ArrayList<AbstractCard> discardPile, ArrayList<AbstractCard> exhaustPile){
        this.hp = hp;
        this.maxHp = maxHp;
        this.gold = gold;
        this.potions = potions;
        this.relics = relics;
        this.block = block;
        this.powers = powers;
        this.powerAmt = powerAmt;
        this.energy = energy;
        this.maxEnergy = maxEnergy;
        this.drawPile = drawPile;
        this.hand = hand;
        this.discardPile = discardPile;
        this.exhaustPile = exhaustPile;

    }
}
