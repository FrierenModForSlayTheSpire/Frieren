package FrierenMod.patches;


import FrierenMod.panels.MagicPanel;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

@SpirePatch2(clz = AbstractPlayer.class, method = "<class>")
public class MPField {
    public static SpireField<MagicPanel> Panel = new SpireField<>(MagicPanel::new);
}
