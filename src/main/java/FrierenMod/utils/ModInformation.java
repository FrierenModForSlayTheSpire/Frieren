package FrierenMod.utils;

import java.util.Locale;

public class ModInformation {
    public final static String MOD_NAME = "FrierenMod";
    public final static String KEY_WORD = MOD_NAME.toLowerCase();
    public static String makeID(String id) {
        return MOD_NAME + ":" + id;
    }
    public static String getResPath(){
        return MOD_NAME + "Resources/";
    }
    public static String getImgPath(){
        return getResPath() + "img/";
    }
    public static String makeLocalizationPath(String lang, String content){
        return getResPath() + "localization/" + lang + "/" + content + ".json";
    }
    public static String getCardStylesPath(){
        return getImgPath() + "cardStyles/";
    }
    public static String getCharPath(){
        return getImgPath() + "char/";
    }
    public static String makeCharResPath(String character, String content){
        return getCharPath() + character + "/" + content + ".png";
    }
    public static String getUIPath(){
        return getImgPath() + "UI/";
    }
    public static String getOrbPath() {
        return getUIPath() + "orb/";
    }
    public static String[] makeOrbTexturePath(String character){
        return new String[]{
                getOrbPath() + character + "/" + "layer5.png",
                getOrbPath() + character + "/" + "layer4.png",
                getOrbPath() + character + "/" + "layer3.png",
                getOrbPath() + character + "/" + "layer2.png",
                getOrbPath() + character + "/" + "layer1.png",
                getOrbPath() + character + "/" + "layer6.png",
                getOrbPath() + character + "/" + "layer5d.png",
                getOrbPath() + character + "/" + "layer4d.png",
                getOrbPath() + character + "/" + "layer3d.png",
                getOrbPath() + character + "/" + "layer2d.png",
                getOrbPath() + character + "/" + "layer1d.png"
        };
    }
    public static String makeOrbVfxPath(String character){
        return getOrbPath() + character + "/" + "vfx.png";
    }
    public static String makeCardBgPath(String character, String cardType, int size) {
        if(size == 512){
            return getCardStylesPath() + character + "/" + "512/" + "bg_" + cardType + "_512.png";
        }else if(size == 1024){
            return getCardStylesPath() + character + "/" + "1024/" + "bg_" + cardType + ".png";
        }else {
            return "ERROR!";
        }
    }
    public static String makeCardOrbPath(String character, String type){
        return getCardStylesPath() + character + "/" + "orb/" + type + "_orb.png";
    }
    public static String makeCardImgPath(String name){
        return getImgPath() + "cards/" + name + ".png";
    }
    public static String makeRelicImgPath(String id){
        return getImgPath() + "relics/" + id + ".png";
    }
}
