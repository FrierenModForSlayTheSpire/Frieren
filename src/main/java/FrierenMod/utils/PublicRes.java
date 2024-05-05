package FrierenMod.utils;

import FrierenMod.powers.AccelerateFlowPower;

public class PublicRes {
    public static final String CHARACTER_NAME = "Public";
    public static final String BG_MANA_512 = ModInformation.makeCardBgPath(CHARACTER_NAME,"magic",512);
    public static final String BG_MANA_1024 = ModInformation.makeCardBgPath(CHARACTER_NAME,"magic",1024);
    public static final String BG_SKILL_FRIEREN_FERN_512 = ModInformation.makeCardBgPath(CHARACTER_NAME, "skill_FrierenFern",512);
    public static final String BG_POWER_FRIEREN_FERN_512 = ModInformation.makeCardBgPath(CHARACTER_NAME, "power_FrierenFern",512);
    public static final String BG_ATTACK_FRIEREN_FERN_512 = ModInformation.makeCardBgPath(CHARACTER_NAME, "attack_FrierenFern",512);
    public static final String BG_SKILL_FRIEREN_FERN_1024 = ModInformation.makeCardBgPath(CHARACTER_NAME, "skill_FrierenFern",1024);
    public static final String BG_POWER_FRIEREN_FERN_1024 = ModInformation.makeCardBgPath(CHARACTER_NAME, "power_FrierenFern",1024);
    public static final String BG_ATTACK_FRIEREN_FERN_1024 = ModInformation.makeCardBgPath(CHARACTER_NAME, "attack_FrierenFern",1024);
    public static final String DEV_CARD_IMG = ModInformation.makeCardImgPath("Strike_Fern");
    public static final String DEV_POWER_IMG_84 = ModInformation.makePowerPath(AccelerateFlowPower.POWER_ID.split(":")[1],84);
    public static final String DEV_POWER_IMG_32 = ModInformation.makePowerPath(AccelerateFlowPower.POWER_ID.split(":")[1],32);
    public static final String MOD_BADGE = ModInformation.makeImgPath("Badge.png");

}
