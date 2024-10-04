package FrierenMod.gameHelpers;

import FrierenMod.patches.fields.CardCrawlGameField;
import FrierenMod.ui.panels.AchievementPopUpPanel;
import FrierenMod.ui.panels.ConfigPanel;
import FrierenMod.ui.slot.Slot;
import FrierenMod.utils.Config;
import FrierenMod.utils.Log;
import FrierenMod.utils.ModInformation;
import FrierenMod.utils.ResourceChecker;
import com.megacrit.cardcrawl.core.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class SlotBgHelper {
    public static String progressString = "0001,0002,0003";
    public static String loadingString = "0001,0002,0003";
    public static String allString = getAllStringFromFiles();

    public static ArrayList<Slot> getLoadingSlotsInPreview() {
        String[] parts = loadingString.split(",");
        if (parts.length != 3) {
            Log.logger.info("LOADING NOT 3 SLOT BG!");
            ArrayList<Slot> defaultSlots = new ArrayList<>();
            defaultSlots.add(new Slot("0001"));
            defaultSlots.add(new Slot("0002"));
            defaultSlots.add(new Slot("0003"));
            return defaultSlots;
        }
        ArrayList<Slot> slots = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Slot slot = new Slot(parts[i]);
            slots.add(slot);
        }
        return slots;
    }


    public static ArrayList<Slot> getLoadingSlots() {
        String[] parts = loadingString.split(",");
        if (parts.length != 3) {
            Log.logger.info("LOADING NOT 3 SLOT BG!");
            ArrayList<Slot> defaultSlots = new ArrayList<>();
            defaultSlots.add(new Slot("0001", 0));
            defaultSlots.add(new Slot("0002", 1));
            defaultSlots.add(new Slot("0003", 2));
            return defaultSlots;
        }
        ArrayList<Slot> slots = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Slot slot = new Slot(parts[i], i);
            slots.add(slot);
        }
        return slots;
    }

    public static String getAllStringFromFiles() {
        ClassLoader classLoader = ResourceChecker.class.getClassLoader();
        URL resource = classLoader.getResource(ModInformation.getSlotBgFolder());
        if (resource == null) {
            Log.logger.info("WHY NO SLOT BG FOLDER?");
            return null;
        }
        String jarPath = resource.getPath().substring(5, resource.getPath().indexOf("!"));
        StringBuilder retVal = new StringBuilder();
        try (JarFile jarFile = new JarFile(jarPath)) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (entryName.startsWith(ModInformation.getSlotBgFolder()) && !entry.isDirectory()) {
                    retVal.append(getId(entryName)).append(",");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (retVal.length() > 0) {
            return retVal.substring(0, retVal.length() - 1);
        }
        Log.logger.info("WHY NO ANY SLOT BG?");
        return "";
    }

    public static ArrayList<Slot> getAllSlotsInLibrary() {
        ArrayList<Slot> slots = new ArrayList<>();
        String[] parts = allString.split(",");
        for (String part : parts) {
            Slot slot = new Slot(part, true);
            slots.add(slot);
        }
        return slots;
    }

    public static int getCollectedSlotBgNumber() {
        String[] parts = progressString.split(",");
        return parts.length;
    }

    private static String getId(String fileName) {
        return fileName.replaceAll(ModInformation.getSlotBgFolder() + "/", "").replaceAll(".png", "");
    }

    public static void changeLoading(int index, String newId) {
        String[] parts = SlotBgHelper.loadingString.split(",");
        parts[index] = newId;
        StringBuilder newLoadingString = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            newLoadingString.append(parts[i]);
            if (i != parts.length - 1)
                newLoadingString.append(",");
        }
        ConfigPanel.saveSlotChange(newLoadingString.toString());
    }

    public static void unlockANewSlot(String id) {
        //temp
        if (Settings.language == Settings.GameLanguage.ENG || id == null || !Config.ALLOW_ACHIEVEMENT || isASlotCollected(id) || !isASlotValid(id)) {
            return;
        }
        String newProgressString = getNewSlotProgressString(id);
        if (Config.IN_DEV)
            Log.logger.info("newProgressString: {}", newProgressString);
        else
            ConfigPanel.saveSlotProgress(newProgressString);
        CardCrawlGameField.achievementPopUpPanelQueue.get().add(new AchievementPopUpPanel(id));
        Log.logger.info("A new slotBg [{}] is collected successfully!", id);
    }

    public static boolean isASlotCollected(String id) {
        return progressString.contains(id);
    }

    public static boolean isASlotValid(String id) {
        if (Config.IN_DEV)
            return true;
        return allString.contains(id);
    }

    public static String getNewSlotProgressString(String id) {
        cleanInvalidIdsInProgressString();
        return progressString + "," + id;
    }

    public static void cleanInvalidIdsInProgressString() {
        String[] parts = progressString.split(",");
        StringBuilder builder = new StringBuilder();
        for (String part : parts) {
            if (allString.contains(part))
                builder.append(part).append(",");
            else
                Log.logger.info("DELETE INVALID ID: {} ", part);
        }
        String newString = builder.toString();
        if (!newString.isEmpty())
            progressString = newString.substring(0, newString.length() - 1);
    }

    public static String rollANewCommonSlotId(char firstChar) {
        String[] parts = allString.split(",");
        ArrayList<String> pool = new ArrayList<>();
        for (String part : parts) {
            if (part.charAt(0) == firstChar) {
                pool.add(part);
            }
        }
        pool.removeIf(id -> progressString.contains(id));
        if (pool.isEmpty()) {
            Log.logger.info("ALL AVAILABLE SLOT BG COLLECTED!");
            return null;
        }
        Collections.shuffle(pool);
        return pool.get(0);
    }
}
