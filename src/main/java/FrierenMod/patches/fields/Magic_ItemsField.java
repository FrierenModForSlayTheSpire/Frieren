package FrierenMod.patches.fields;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.runHistory.RunHistoryScreen;
import com.megacrit.cardcrawl.screens.runHistory.TinyCard;

import java.util.ArrayList;

@SpirePatch(clz = RunHistoryScreen.class, method = "<class>")
public class Magic_ItemsField {
    public static SpireField<ArrayList<TinyCard>> magic_items = new SpireField<>(ArrayList::new);
    public static SpireField<ArrayList<AbstractCard>> loaded_factors = new SpireField<>(ArrayList::new);
}
