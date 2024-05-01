package FrierenMod.gameHelpers.Savables;

import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class SerializablePlayer {
    public static Object[] toObjectArray(AbstractPlayer p) {
        Object[] playerArray = new Object[2];
        playerArray[0] = p.maxHealth;
        playerArray[1] = p.currentHealth;
        return playerArray;
    }

    public static void toAbstractPlayer(AbstractPlayer p, Object[] sp) {
        if(sp[0] != null)
            p.maxHealth = (int) sp[0];
        if(sp[1] != null)
            p.currentHealth = (int) sp[1];
    }
}
