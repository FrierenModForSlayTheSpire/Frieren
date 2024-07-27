package FrierenMod.patches.fields;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(clz = AbstractPlayer.class, method = "<class>")
public class MagicItemBagField {
    public static SpireField<CardGroup> magicItemBag = new SpireField<>(() -> null);
    public static CardGroup getBag() {
        return magicItemBag.get(AbstractDungeon.player);
    }
}
