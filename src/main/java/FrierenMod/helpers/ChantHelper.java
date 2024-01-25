package FrierenMod.helpers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static FrierenMod.tags.CustomTags.MAGIC_POWER;

public class ChantHelper {



    public int getMagicPowerNumInDrawPile(){
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.tags.contains(MAGIC_POWER)) {
                counts++;
            }
        }
        return counts;
    }
    public int getMagicPowerNumInHand(){
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.tags.contains(MAGIC_POWER)) {
                counts++;
            }
        }
        return counts;
    }
    public int getMagicPowerNumInDiscardPile(){
        int counts = 0;
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.tags.contains(MAGIC_POWER)) {
                counts++;
            }
        }
        return counts;
    }
    public int getAllMagicPowerNum(){
        return this.getMagicPowerNumInDrawPile() + this.getMagicPowerNumInHand() + this.getMagicPowerNumInDiscardPile();
    }
    public boolean cannotChant(int x){
        return !this.canChantFromDrawPile(x) && !this.canChantFromHand(x) && !this.canChantFromDiscardPile(x);
    }
    public boolean canChantFromDrawPile(int x){
        int num = this.getMagicPowerNumInDrawPile();
        return x <= num;
    }
    public boolean canChantFromHand(int x){
        int num = this.getMagicPowerNumInHand();
        return x <= num;
    }
    public boolean canChantFromDiscardPile(int x){
        int num = this.getMagicPowerNumInDiscardPile();
        return x <= num;
    }
    public boolean canChantUse(AbstractCard c, AbstractPlayer p, AbstractMonster m, int x){
        if (c.type == AbstractCard.CardType.STATUS && c.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Medical Kit")) {
            return false;
        } else if (c.type == AbstractCard.CardType.CURSE && c.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Blue Candle")) {
            return false;
        } else if (this.cannotChant(x)){
            return false;
        }
        else {
            return c.cardPlayable(m) && c.hasEnoughEnergy();
        }
    }
}
