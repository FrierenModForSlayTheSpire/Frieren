package FrierenMod.utils;

import basemod.abstracts.CustomMultiPageFtue;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipTracker;
import com.megacrit.cardcrawl.localization.UIStrings;

import static com.megacrit.cardcrawl.core.Settings.language;

public class FTUEUtils {
    public static final String COMBAT_TIP_KEY = ModInformation.makeID("FRIEREN_COMBAT_TIP");

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ModInformation.makeID("FTUE"));

    private static final Texture[] TEXTURES_ZHS = new Texture[]{ImageMaster.loadImage(FrierenRes.TIP_01_ZHS),
            ImageMaster.loadImage(FrierenRes.TIP_02_ZHS), ImageMaster.loadImage(FrierenRes.TIP_03_ZHS)};
    private static final Texture[] TEXTURES_ENG = new Texture[]{ImageMaster.loadImage(FrierenRes.TIP_01_ENG),
            ImageMaster.loadImage(FrierenRes.TIP_02_ENG), ImageMaster.loadImage(FrierenRes.TIP_03_ENG)};

    public static void openCombatTip() {
        if (!(Boolean) TipTracker.tips.get(COMBAT_TIP_KEY)) {
            if (language == Settings.GameLanguage.ZHS)
                AbstractDungeon.ftue = new CustomMultiPageFtue(TEXTURES_ZHS, uiStrings.TEXT);
            else
                AbstractDungeon.ftue = new CustomMultiPageFtue(TEXTURES_ENG, uiStrings.TEXT);
            TipTracker.neverShowAgain(COMBAT_TIP_KEY);
        }
    }
}
