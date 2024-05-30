package FrierenMod;


import FrierenMod.Characters.Fern;
import FrierenMod.Characters.Frieren;
import FrierenMod.cards.optionCards.ChantOptions.AbstractMagicFactor;
import FrierenMod.enums.CardEnums;
import FrierenMod.enums.CharacterEnums;
import FrierenMod.gameHelpers.*;
import FrierenMod.monsters.Spiegel_Frieren;
import FrierenMod.potions.BottledMana;
import FrierenMod.potions.DissolveClothPotion;
import FrierenMod.potions.EmperorWine;
import FrierenMod.ui.panels.MagicFactorDeckPanel;
import FrierenMod.ui.screens.MagicFactorDeckScreen;
import FrierenMod.utils.*;
import FrierenMod.variables.ChantXVariable;
import FrierenMod.variables.RaidVariable;
import FrierenMod.variables.SecondMagicNumberVariable;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import basemod.interfaces.*;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.DynamicTextBlocks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static FrierenMod.ui.panels.ConfigPanel.*;
import static basemod.BaseMod.logger;
import static com.megacrit.cardcrawl.core.Settings.language;


@SpireInitializer
public class ModManager implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber, PostInitializeSubscriber, CustomSavable<String> {
    private static String modID;
    public static final DataObject saveData = new DataObject();

    public ModManager() {
        BaseMod.subscribe(this);
        setModID(ModInformation.MOD_NAME);
        Log.logger.info("Creating the color " + CardEnums.FRIEREN_CARD.toString());
        BaseMod.addColor(CardEnums.FRIEREN_CARD,
                FrierenRes.RENDER_COLOR.cpy(),
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
        if (Config.FERN_ENABLE)
            BaseMod.addColor(CardEnums.FERN_CARD,
                    FernRes.RENDER_COLOR.cpy(),
                    FernRes.RENDER_COLOR.cpy(),
                    FernRes.RENDER_COLOR.cpy(),
                    FernRes.RENDER_COLOR.cpy(),
                    FernRes.RENDER_COLOR.cpy(),
                    FernRes.RENDER_COLOR.cpy(),
                    FernRes.RENDER_COLOR.cpy(),
                    FernRes.BG_ATTACK_512,
                    FernRes.BG_SKILL_512,
                    FernRes.BG_POWER_512,
                    FernRes.ENERGY_ORB,
                    FernRes.BG_ATTACK_1024,
                    FernRes.BG_SKILL_1024,
                    FernRes.BG_POWER_1024,
                    FernRes.BIG_ORB,
                    FernRes.SMALL_ORB);
        Log.logger.info("Done creating the color");
        Log.logger.info("Adding hooks...");
        BaseMod.subscribe(new OnPlayerTurnStartHelper());
        BaseMod.subscribe(new OnStartBattleHelper());
        Log.logger.info("Done adding hooks");
        BaseMod.addSaveField(modID, this);
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
        SpireConfig config = makeConfig();
        loadProperties(config);
    }

    @Override
    public void receivePostInitialize() {
        makeModPanels();
        BaseMod.addAudio("Frieren_The_Slayer.mp3", ModInformation.makeAudioPath("sound/Frieren_The_Slayer.mp3"));
        BaseMod.addMonster(Spiegel_Frieren.MONSTER_ID, Spiegel_Frieren::new);
        BaseMod.addBoss(TheBeyond.ID, Spiegel_Frieren.MONSTER_ID,
                MonsterRes.SPIEGEL_BOSS_ICON_1,
                MonsterRes.SPIEGEL_BOSS_ICON_2);
        BaseMod.addTopPanelItem(new MagicFactorDeckPanel());
        BaseMod.addCustomScreen(new MagicFactorDeckScreen());
    }

    @Override
    public void receiveEditCards() {
        pathCheck();
        Log.logger.info("Adding variables");
        BaseMod.addDynamicVariable(new ChantXVariable());
        BaseMod.addDynamicVariable(new SecondMagicNumberVariable());
        BaseMod.addDynamicVariable(new RaidVariable());
        DynamicTextBlocks.registerCustomCheck("frierenmod:SlotNumber", card -> {
            if (AbstractDungeon.player != null && CombatHelper.isInCombat()) {
                if (card instanceof AbstractMagicFactor) {
                    System.out.println(((AbstractMagicFactor) card).slotNumber);
                    return ((AbstractMagicFactor) card).slotNumber;
                }
            }
            return -1;
        });
        Log.logger.info("Done adding variables");
        Log.logger.info("Adding cards");
        String optionCardsClassPath = getModID() + ".cards.optionCards";
        String tempCardsClassPath = getModID() + ".cards.tempCards";
        String FrierenCardsClassPath = getModID() + ".cards.white";
        String FernCardsClassPath = getModID() + ".cards.purple";
        (new AutoAdd(getModID())).packageFilter(optionCardsClassPath).setDefaultSeen(true).any(AbstractCard.class, (info, card) -> {
            BaseMod.addCard(card);
            if (Config.IN_DEV && info.seen)
                UnlockTracker.unlockCard(card.cardID);
        });
        (new AutoAdd(getModID())).packageFilter(tempCardsClassPath).setDefaultSeen(true).any(AbstractCard.class, (info, card) -> {
            BaseMod.addCard(card);
            if (Config.IN_DEV && info.seen)
                UnlockTracker.unlockCard(card.cardID);
        });
        (new AutoAdd(getModID())).packageFilter(FrierenCardsClassPath).setDefaultSeen(true).any(AbstractCard.class, (info, card) -> {
            BaseMod.addCard(card);
            if (Config.IN_DEV && info.seen)
                UnlockTracker.unlockCard(card.cardID);
        });
        if (Config.FERN_ENABLE)
            (new AutoAdd(getModID())).packageFilter(FernCardsClassPath).setDefaultSeen(true).any(AbstractCard.class, (info, card) -> {
                BaseMod.addCard(card);
                if (Config.IN_DEV && info.seen)
                    UnlockTracker.unlockCard(card.cardID);
            });
//        for (AbstractCard c: CardPoolHelper.getFrierenCardPool())
//            BaseMod.addCard(c);
//        for (AbstractCard c: CardPoolHelper.getFernCardPool())
//            BaseMod.addCard(c);
        for (AbstractCard c : CardPoolHelper.getBaseFrierenFernCardPool()) {
            BaseMod.addCard(c);
            if (Config.IN_DEV)
                UnlockTracker.unlockCard(c.cardID);
        }
        Log.logger.info("Done adding cards!");
    }

    @Override
    public void receiveEditRelics() {
        String relicClassPath = getClass().getPackage().getName() + ".relics";
        Log.logger.info("===============Adding relics: search in " + relicClassPath);
        for (ModInfo info : Loader.MODINFOS)
            Log.logger.info(info.ID);
        (new AutoAdd(getModID())).packageFilter(relicClassPath).any(CustomRelic.class, (info, relic) -> {
            BaseMod.addRelicToCustomPool(relic, CardEnums.FRIEREN_CARD);
            UnlockTracker.markRelicAsSeen(relic.relicId);
            Log.logger.info("Adding relics: " + relic.relicId);
        });
        Log.logger.info("Done adding relics!");
    }

    @Override
    public void receiveEditCharacters() {
        Log.logger.info("Beginning to edit characters. Add " + CharacterEnums.FRIEREN.toString());
        BaseMod.addCharacter(new Frieren(CardCrawlGame.playerName), FrierenRes.CHARACTER_BUTTON, FrierenRes.CHARACTER_PORTRAIT, CharacterEnums.FRIEREN);
        Log.logger.info("Added " + CharacterEnums.FRIEREN.toString());
        if (Config.FERN_ENABLE) {
            Log.logger.info("Beginning to edit characters. Add " + CharacterEnums.FERN.toString());
            BaseMod.addCharacter(new Fern(CardCrawlGame.playerName), FernRes.CHARACTER_BUTTON, FernRes.CHARACTER_PORTRAIT, CharacterEnums.FERN);
            Log.logger.info("Added " + CharacterEnums.FERN.toString());
        }
        Log.logger.info("Beginning to add potions.");
        BaseMod.addPotion(BottledMana.class, Color.BLUE.cpy(), Color.ROYAL.cpy(), Color.ROYAL, BottledMana.POTION_ID, CharacterEnums.FRIEREN);
        BaseMod.addPotion(DissolveClothPotion.class, new Color(149.0F / 255.0F, 122.0F / 255.0F, 157.0F / 255.0F, 1.0F), new Color(149.0F / 255.0F, 122.0F / 255.0F, 157.0F / 255.0F, 1.0F), FrierenRes.RENDER_COLOR.cpy(), DissolveClothPotion.POTION_ID, CharacterEnums.FRIEREN);
        BaseMod.addPotion(EmperorWine.class, FrierenRes.RENDER_COLOR.cpy(), FrierenRes.RENDER_COLOR.cpy(), FrierenRes.RENDER_COLOR.cpy(), EmperorWine.POTION_ID, CharacterEnums.FRIEREN);
        Log.logger.info("Added potions.");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang;
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
//            lang = "ZHS";
            lang = "ENG";
        }
        String json = Gdx.files.internal(ModInformation.makeLocalizationPath(lang, "keywords"))
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(ModInformation.KEY_WORD, keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
                Log.logger.info("-----------------add keyword: " + keyword.NAMES[0]);
            }
        }
    }

    public void receiveEditStrings() {
        String lang;
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
//            lang = "ZHS";
            lang = "ENG";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, ModInformation.makeLocalizationPath(lang, "cards"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class, ModInformation.makeLocalizationPath(lang, "characters"));
        BaseMod.loadCustomStringsFile(RelicStrings.class, ModInformation.makeLocalizationPath(lang, "relics"));
        BaseMod.loadCustomStringsFile(PotionStrings.class, ModInformation.makeLocalizationPath(lang, "potions"));
        BaseMod.loadCustomStringsFile(PowerStrings.class, ModInformation.makeLocalizationPath(lang, "powers"));
        BaseMod.loadCustomStringsFile(UIStrings.class, ModInformation.makeLocalizationPath(lang, "UIs"));
        BaseMod.loadCustomStringsFile(MonsterStrings.class, ModInformation.makeLocalizationPath(lang, "monsters"));
    }

    private static String getModID() {
        return modID;
    }

    private static void pathCheck() {
        Gson coolG = new Gson();
        InputStream in = ModManager.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = (IDCheckDontTouchPls) coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        String packageName = ModManager.class.getPackage().getName();
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources");
        if (!modID.equals(EXCEPTION_STRINGS.DEV_ID)) {
            if (!packageName.equals(getModID()))
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID());
            if (!resourcePathExists.exists())
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources");
        }
    }

    @Override
    public final String onSave() {
        // 可以直接在抽象类存储一些共有的数据，例如 secondCounter
        return saveData.save();
    }


    @Override
    public final void onLoad(String s) {
        saveData.load(s);
    }
}