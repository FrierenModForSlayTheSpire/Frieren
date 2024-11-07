package FrierenMod.gameHelpers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

public class State {
    public final int hp;
    public final int maxHp;
    public final int gold;
    public final ArrayList<AbstractPotion> potions;
    public final ArrayList<AbstractRelic> relics;
    public final int block;
    public final ArrayList<AbstractPower> powers;
    public final int energy;
    public final int maxEnergy;
    public final ArrayList<AbstractCard> drawPile;
    public final ArrayList<AbstractCard> hand;
    public final ArrayList<AbstractCard> discardPile;
    public final ArrayList<AbstractCard> exhaustPile;
    public final ArrayList<AbstractCard> cardsPlayedThisTurn;
    public State(int hp, int maxHp, int gold, ArrayList<AbstractPotion> potions, ArrayList<AbstractRelic> relics, int block, ArrayList<AbstractPower> powers, int energy, int maxEnergy, ArrayList<AbstractCard> drawPile, ArrayList<AbstractCard> hand, ArrayList<AbstractCard> discardPile, ArrayList<AbstractCard> exhaustPile, ArrayList<AbstractCard> cardsPlayedThisTurn){
        this.hp = hp;
        this.maxHp = maxHp;
        this.gold = gold;
        this.potions = potions;
        this.relics = relics;
        this.block = block;
        this.powers = powers;
        this.energy = energy;
        this.maxEnergy = maxEnergy;
        this.drawPile = drawPile;
        this.hand = hand;
        this.discardPile = discardPile;
        this.exhaustPile = exhaustPile;
        this.cardsPlayedThisTurn = cardsPlayedThisTurn;
    }
}
