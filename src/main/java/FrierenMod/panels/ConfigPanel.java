package FrierenMod.panels;

import FrierenMod.utils.Log;
import FrierenMod.utils.ModInformation;
import FrierenMod.utils.PublicRes;
import basemod.BaseMod;
import basemod.IUIElement;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Properties;

public class ConfigPanel extends ModPanel {
    public static final String[] AUTHORS = new String[]{"Arkalin", "Figure"};
    public static boolean ALLOW_SPECIAL_SFX = true;

    @Nullable
    public static SpireConfig makeConfig() {
        Properties properties = new Properties();
        properties.setProperty("ALLOW_SPECIAL_SFX", Boolean.toString(ALLOW_SPECIAL_SFX));
        try {
            return new SpireConfig(ModInformation.MOD_NAME, "FrierenModConfig", properties);
        } catch (Exception e) {
            return null;
        }
    }

    public static void loadProperties(SpireConfig config) {
        if (config == null) {
            Log.logger.info("Missing config file");
            return;
        }
        ALLOW_SPECIAL_SFX = config.getBool("ALLOW_SPECIAL_SFX");
    }

    private static void save(SpireConfig config) {
        if (config == null)
            return;
        try {
            config.save();
        } catch (Exception e) {
            Log.logger.info("Failed to save current rs.lunarshop.config file");
        }
    }

    public static void SaveConfig() {
        SpireConfig config = makeConfig();
        assert config != null;
        config.setBool("ALLOW_SPECIAL_SFX", ALLOW_SPECIAL_SFX);
        save(config);
    }

    public static void makeModPanels() {
        ModPanel settings = new ModPanel();
        ModLabeledToggleButton allowSFX = new ModLabeledToggleButton("Special SFX", 380.0F, 720.0F, Color.WHITE.cpy(), FontHelper.charDescFont, ALLOW_SPECIAL_SFX, settings, l -> {

        }, btn -> {
            ALLOW_SPECIAL_SFX = btn.enabled;
            SpireConfig config = makeConfig();
            assert config != null;
            config.setBool("ALLOW_SPECIAL_SFX", ALLOW_SPECIAL_SFX);
            save(config);
        });
        settings.addUIElement((IUIElement) allowSFX);
        BaseMod.registerModBadge(ImageMaster.loadImage(PublicRes.MOD_BADGE), "FrierenMod", Arrays.toString((Object[]) AUTHORS), "An original character", settings);
    }
}
