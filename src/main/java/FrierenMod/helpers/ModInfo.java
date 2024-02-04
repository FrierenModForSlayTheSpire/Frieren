package FrierenMod.helpers;

public class ModInfo {
    private final static String MOD_NAME = "FrierenMod";
    public static String makeID(String id) {
        return MOD_NAME + ":" + id;
    }
}
