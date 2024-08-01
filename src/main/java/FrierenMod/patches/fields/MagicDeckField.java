package FrierenMod.patches.fields;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(clz = AbstractPlayer.class, method = "<class>")
public class MagicDeckField {
    public static SpireField<CardGroup> magicDeck = new SpireField<>(() -> null);
    public static CardGroup getDeck() {
        return magicDeck.get(AbstractDungeon.player);
    }
}
