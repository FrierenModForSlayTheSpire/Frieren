package FrierenMod;


import FrierenMod.Characters.Frieren;
import FrierenMod.utils.FrierenRes;
import FrierenMod.utils.IDCheckDontTouchPls;
import FrierenMod.utils.Log;
import FrierenMod.utils.ModInformation;
import FrierenMod.variables.ChantXVariable;
import FrierenMod.variables.SecondMagicNumberVariable;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static FrierenMod.Characters.Frieren.Enums.FRIEREN;
import static FrierenMod.Characters.Frieren.Enums.FRIEREN_CARD;
import static basemod.BaseMod.logger;
import static com.megacrit.cardcrawl.core.Settings.language;


@SpireInitializer
public class ModManager implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber {
    private static String modID;
    public ModManager() {
        BaseMod.subscribe(this);
        setModID(ModInformation.MOD_NAME);
        Log.logger.info("Creating the color " + FRIEREN_CARD.toString());
        BaseMod.addColor(FRIEREN_CARD, FrierenRes.RENDER_COLOR.cpy(),
                FrierenRes.RENDER_COLOR.cpy(),
                FrierenRes.RENDER_COLOR.cpy(),
                FrierenRes.RENDER_COLOR.cpy(),
                FrierenRes.RENDER_COLOR.cpy(),
                FrierenRes.RENDER_COLOR.cpy(),
                FrierenRes.RENDER_COLOR.cpy(),
                FrierenRes.BG_ATTACK_512,
                FrierenRes.BG_SKILL_512,
                FrierenRes.BG_POWER_512,
                FrierenRes.ENERGY_ORB,
                FrierenRes.BG_ATTACK_1024,
                FrierenRes.BG_SKILL_1024,
                FrierenRes.BG_POWER_1024,
                FrierenRes.BIG_ORB,
                FrierenRes.SMALL_ORB);
        Log.logger.info("Done creating the color");
    }

    public void setModID(String ID) {
        Gson coolG = new Gson();
        InputStream in = ModManager.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        logger.info("You are attempting to set your mod ID as: " + ID);
        if (ID.equals(EXCEPTION_STRINGS.DEFAULT_ID))
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION);
        if (ID.equals(EXCEPTION_STRINGS.DEV_ID)) {
            modID = EXCEPTION_STRINGS.DEFAULT_ID;
        } else {
            modID = ID;
        }
        logger.info("Success! ID is " + modID);
    }
    public static void initialize() {
        new ModManager();
    }
    @Override
    public void receiveEditCards() {
        pathCheck();
        Log.logger.info("Adding variables");
        BaseMod.addDynamicVariable(new ChantXVariable());
        BaseMod.addDynamicVariable(new SecondMagicNumberVariable());
        Log.logger.info("Done adding variables");
        Log.logger.info("Adding cards");
        String cardsClassPath = getModID() + ".cards";
        (new AutoAdd(getModID())).packageFilter(cardsClassPath).setDefaultSeen(true).any(AbstractCard.class, (info, card) -> {
            BaseMod.addCard(card);
//            if (info.seen)
//                UnlockTracker.unlockCard(card.cardID);
        });
        Log.logger.info("Done adding cards!");
    }
    @Override
    public void receiveEditRelics() {
        String relicClassPath = getClass().getPackage().getName() + ".relics";
        Log.logger.info("===============Adding relics: search in " + relicClassPath);
        for (ModInfo info : Loader.MODINFOS)
            Log.logger.info(info.ID);
        (new AutoAdd(getModID())).packageFilter(relicClassPath).any(CustomRelic.class, (info, relic) -> {
            BaseMod.addRelicToCustomPool(relic, FRIEREN_CARD);
            UnlockTracker.markRelicAsSeen(relic.relicId);
            Log.logger.info("Adding relics: " + relic.relicId);
        });
        Log.logger.info("Done adding relics!");
    }
    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new Frieren(CardCrawlGame.playerName), FrierenRes.CHARACTER_BUTTON, FrierenRes.CHARACTER_PORTRAIT, FRIEREN);

    }
    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang;
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ENG";
        }
        String json = Gdx.files.internal(ModInformation.makeLocalizationPath(lang,"keywords"))
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(ModInformation.KEY_WORD, keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
    public void receiveEditStrings() {
        String lang;
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ENG";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, ModInformation.makeLocalizationPath(lang,"cards"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class, ModInformation.makeLocalizationPath(lang,"characters"));
        BaseMod.loadCustomStringsFile(RelicStrings.class, ModInformation.makeLocalizationPath(lang,"relics"));
        BaseMod.loadCustomStringsFile(PowerStrings.class, ModInformation.makeLocalizationPath(lang,"powers"));
        BaseMod.loadCustomStringsFile(UIStrings.class,ModInformation.makeLocalizationPath(lang,"UIs"));
    }

    private static String getModID() {
        return modID;
    }
    private static void pathCheck() {
        Gson coolG = new Gson();
        InputStream in = ModManager.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = (IDCheckDontTouchPls)coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        String packageName = ModManager.class.getPackage().getName();
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources");
        if (!modID.equals(EXCEPTION_STRINGS.DEV_ID)) {
            if (!packageName.equals(getModID()))
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID());
            if (!resourcePathExists.exists())
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources");
        }
    }
}