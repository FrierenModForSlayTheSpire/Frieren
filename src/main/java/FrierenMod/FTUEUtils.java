package FrierenMod;

import FrierenMod.utils.ModInformation;
import basemod.abstracts.CustomMultiPageFtue;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipTracker;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.FtueTip;

public class FTUEUtils {
    public static final String COMBAT_TIP_KEY = ModInformation.makeID("COMBAT_TIP");

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ModInformation.makeID("FTUE"));

    private static final Texture[] TEXTURES = new Texture[] { ImageMaster.loadImage(ModInformation.makeUIPath("tip01")),
            ImageMaster.loadImage(ModInformation.makeUIPath("tip02")) };

    public static void openCombatTip() {
        if (!(Boolean) TipTracker.tips.get(COMBAT_TIP_KEY)) {
            AbstractDungeon.ftue = new CustomMultiPageFtue(TEXTURES, uiStrings.TEXT);
            TipTracker.neverShowAgain(COMBAT_TIP_KEY);
        }
    }
}
