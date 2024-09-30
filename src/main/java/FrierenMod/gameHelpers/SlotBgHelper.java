package FrierenMod.gameHelpers;

import FrierenMod.ui.panels.ConfigPanel;
import FrierenMod.ui.slot.Slot;
import FrierenMod.utils.Log;
import FrierenMod.utils.ModInformation;
import FrierenMod.utils.ResourceChecker;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class SlotBgHelper {
    public static String progressString = "0001,0002,0003";
    public static String loadingString = "0001,0002,0003";

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

    public static ArrayList<Slot> getAllSlotsFromFiles() {
        ClassLoader classLoader = ResourceChecker.class.getClassLoader();
        URL resource = classLoader.getResource(ModInformation.getSlotBgFolder());
        if (resource == null) {
            Log.logger.info("WHY NO SLOT BG FOLDER?");
            return null;
        }
        String jarPath = resource.getPath().substring(5, resource.getPath().indexOf("!"));
        ArrayList<Slot> slots = new ArrayList<>();
        try (JarFile jarFile = new JarFile(jarPath)) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (entryName.startsWith(ModInformation.getSlotBgFolder()) && !entry.isDirectory()) {
                    slots.add(new Slot(getId(entryName), true));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return slots;
    }

    public static int getAllSlotBgNumber() {
        if (getAllSlotsFromFiles() == null)
            return 0;
        return getAllSlotsFromFiles().size();
    }

    public static int getCollectedSlotBgNumber() {
        String[] parts = loadingString.split(",");
        return parts.length;
    }

    private static String getId(String fileName) {
        return fileName.replaceAll(ModInformation.getSlotBgFolder() + "/", "").replaceAll(".png", "");
    }

    public static float getProgressBarPercent() {
        if (getAllSlotBgNumber() == 0) {
            Log.logger.info("Error while getting progress percent!");
            return 0;
        }
        return (float) getCollectedSlotBgNumber() / getAllSlotBgNumber();
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
}
