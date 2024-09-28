package FrierenMod.utils;

import FrierenMod.powers.AccelerateFlowPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.helpers.CardHelper;

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
    public static final String MAGIC_DECK_ICON = ModInformation.makeIconPath("magic_deck");
    public static final String MAGIC_ITEM_REWARD_ICON = ModInformation.makeIconPath("magic_item_reward");
    public static final String CARD_BANNER_PROP = ModInformation.makeCardBannerPath(CHARACTER_NAME,"prop","small");
    public static final String CARD_BANNER_PROP_L = ModInformation.makeCardBannerPath(CHARACTER_NAME,"prop","large");
    public static final Color MAGIC_ITEM_RENDER_COLOR = CardHelper.getColor(157, 68, 250);
    public static final String BG_MAGIC_FACTOR_512 = BG_MANA_512;
    public static final String BG_MAGIC_FACTOR_1024 = BG_MANA_1024;
    public static final String SLOT_1 = ModInformation.makeUIPath("slotBg/slot1");
    public static final String SLOT_2 = ModInformation.makeUIPath("slotBg/slot2");
    public static final String SLOT_3 = ModInformation.makeUIPath("slotBg/slot3");
}
