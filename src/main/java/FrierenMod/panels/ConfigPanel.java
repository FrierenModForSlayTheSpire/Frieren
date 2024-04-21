package FrierenMod.panels;

import FrierenMod.utils.Config;
import FrierenMod.utils.Log;
import FrierenMod.utils.ModInformation;
import FrierenMod.utils.PublicRes;
import basemod.BaseMod;
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
    private static final float BASE_X_POSE = 380.0F;
    private static final float BASE_Y_POSE = 720.0F;
    private static final float GAP = 40.0F;

    @Nullable
    public static SpireConfig makeConfig() {
        Properties properties = new Properties();
        properties.setProperty("ALLOW_SPECIAL_SFX", Boolean.toString(Config.ALLOW_SPECIAL_SFX));
        properties.setProperty("REMOVE_VELVET_CHOKER", Boolean.toString(Config.REMOVE_VELVET_CHOKER));
        properties.setProperty("REMOVE_DEAD_BRANCH", Boolean.toString(Config.REMOVE_DEAD_BRANCH));
        properties.setProperty("REMOVE_STRANGE_SPOON", Boolean.toString(Config.REMOVE_STRANGE_SPOON));
        properties.setProperty("REPLACE_CORRUPT_HEART", Boolean.toString(Config.REPLACE_CORRUPT_HEART));
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
        Config.ALLOW_SPECIAL_SFX = config.getBool("ALLOW_SPECIAL_SFX");
        Config.REMOVE_VELVET_CHOKER = config.getBool("REMOVE_VELVET_CHOKER");
        Config.REMOVE_DEAD_BRANCH = config.getBool("REMOVE_DEAD_BRANCH");
        Config.REMOVE_STRANGE_SPOON = config.getBool("REMOVE_STRANGE_SPOON");
        Config.REPLACE_CORRUPT_HEART = config.getBool("REPLACE_CORRUPT_HEART");
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
        config.setBool("ALLOW_SPECIAL_SFX", Config.ALLOW_SPECIAL_SFX);
        config.setBool("REMOVE_VELVET_CHOKER", Config.REMOVE_VELVET_CHOKER);
        config.setBool("REMOVE_DEAD_BRANCH", Config.REMOVE_DEAD_BRANCH);
        config.setBool("REMOVE_STRANGE_SPOON", Config.REMOVE_STRANGE_SPOON);
        config.setBool("REPLACE_CORRUPT_HEART", Config.REPLACE_CORRUPT_HEART);
        save(config);
    }

    public static void makeModPanels() {
        ModPanel settings = new ModPanel();
        ModLabeledToggleButton allowSFX = new ModLabeledToggleButton("Special SFX", BASE_X_POSE, BASE_Y_POSE, Color.WHITE.cpy(), FontHelper.charDescFont, Config.ALLOW_SPECIAL_SFX, settings, l -> {
        }, btn -> {
            Config.ALLOW_SPECIAL_SFX = btn.enabled;
            SpireConfig config = makeConfig();
            assert config != null;
            config.setBool("ALLOW_SPECIAL_SFX", Config.ALLOW_SPECIAL_SFX);
            save(config);
        });
        ModLabeledToggleButton removeVelvetChoker = new ModLabeledToggleButton("Remove Velvet Choker when playing Frieren", BASE_X_POSE, BASE_Y_POSE - GAP, Color.WHITE.cpy(), FontHelper.charDescFont, Config.REMOVE_VELVET_CHOKER, settings, l -> {
        }, btn -> {
            Config.REMOVE_VELVET_CHOKER = btn.enabled;
            SpireConfig config = makeConfig();
            assert config != null;
            config.setBool("REMOVE_VELVET_CHOKER", Config.REMOVE_VELVET_CHOKER);
            save(config);
        });
        ModLabeledToggleButton removeDeadBranch = new ModLabeledToggleButton("Remove Dead Branch when playing Frieren", BASE_X_POSE, BASE_Y_POSE - 2 * GAP, Color.WHITE.cpy(), FontHelper.charDescFont, Config.REMOVE_DEAD_BRANCH, settings, l -> {
        }, btn -> {
            Config.REMOVE_DEAD_BRANCH = btn.enabled;
            SpireConfig config = makeConfig();
            assert config != null;
            config.setBool("REMOVE_DEAD_BRANCH", Config.REMOVE_DEAD_BRANCH);
            save(config);
        });
        ModLabeledToggleButton removeStrangeSpoon = new ModLabeledToggleButton("Remove Strange Spoon when playing Frieren", BASE_X_POSE, BASE_Y_POSE - 3 * GAP, Color.WHITE.cpy(), FontHelper.charDescFont, Config.REMOVE_STRANGE_SPOON, settings, l -> {
        }, btn -> {
            Config.REMOVE_STRANGE_SPOON = btn.enabled;
            SpireConfig config = makeConfig();
            assert config != null;
            config.setBool("REMOVE_STRANGE_SPOON", Config.REMOVE_STRANGE_SPOON);
            save(config);
        });
        ModLabeledToggleButton replaceHeart = new ModLabeledToggleButton("100% encounter Spiegel when playing Frieren in The Beyond.", BASE_X_POSE, BASE_Y_POSE - 4 * GAP, Color.WHITE.cpy(), FontHelper.charDescFont, Config.REPLACE_CORRUPT_HEART, settings, l -> {
        }, btn -> {
            Config.REPLACE_CORRUPT_HEART = btn.enabled;
            SpireConfig config = makeConfig();
            assert config != null;
            config.setBool("REPLACE_CORRUPT_HEART", Config.REPLACE_CORRUPT_HEART);
            save(config);
        });
        settings.addUIElement(allowSFX);
        settings.addUIElement(removeVelvetChoker);
        settings.addUIElement(removeDeadBranch);
        settings.addUIElement(removeStrangeSpoon);
        settings.addUIElement(replaceHeart);
        BaseMod.registerModBadge(ImageMaster.loadImage(PublicRes.MOD_BADGE), "FrierenMod", Arrays.toString(AUTHORS), "An original character", settings);
    }
}
